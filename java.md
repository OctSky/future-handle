#ToString

1、org.apache.commons.lang.builder.ToStringBuilder#reflectionToString
2、Guava: MoreStringHelper.ToString

#date & time
DateTimeFormatter
LocalDate.parse()  &  format()



#rest request

##连接建立与认证
package：javax.net.ssl

建立SSL盲信连接上下文：
private sslContext buildSSLContextWithBlindTrust(){
       SSLContext sslContext = null;

        try {
            sslContext = SSLContext.getInstance("SSL", "SunJSSE");

            sslContext.init(null,new TrustManager[] {new X509TrustManager(){...}}, new java.security.SecureRandom());
        } catch (KeyManagementException | NoSuchProviderException | NoSuchAlgorithmException e) {
        	e.printStackTrace();
        }

        return sslContext;
}

用户账户密码认证
class: java.net.Authenticator

    private static void setupDefaultAuthenticator(String userName, String password) {
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password.toCharArray());
            }
        });
    }

构建主机名解析器
    private static HostnameVerifier buildHostnameVerifier() {
        return new HostnameVerifier() {
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        };
    }

建立SSL连接：
    private void setupSSLContext(String username, String password) {
        SSLContext sslContext = buildSSLContextWithBlindTrustManager();

        setupDefaultAuthenticator(username, password);

        HttpsURLConnection.setDefaultHostnameVerifier(buildHostnameVerifier());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
    }


建立HTTP连接：
class: javax.net.ssl.HttpsURLConnection

private HttpsURLConnection buildConnection(String urlStr){
	HttpsURLConnection connection = null;
	try{
		URL url=new URL(urlStr);
		connection= (HttpsURLConnection) url.openConnection();

		connection.setConnectionTimeout(5000);
		connection.setReadTimeout(6000);

		return connection;
	}catch(Exception e){
		throw e;
	}
}

读取连接数据流：
DateInputStream input= new DateInputStream(connection.getInputStream());

读取连接文件目录：
class: org.jsoup.Jsoup、org.jsoup.nodes.Document、org.jsoup.nodes.Element

    public List<String> getFileOrDirListFromUrl(String username, String password, String url) {

        setupSSLContext(username, password);

        List<String> result = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
            if (doc != null) {
                for (Element element : doc.select(FILE_CSS_QUERY)) {
                    String name = element.attr(FILE_ATTR);
                    if (StringUtil.isNotBlank(name)) {
                        result.add(name);
                    }
                }
            }
        } catch (Exception e) {
        	throw e;
        }
        return result;
    }



#代理与切面
org.springframework.aop.framework.ProxyFactoryBean
如：
<bean id="stagedPaymentService" class="org.springframework.aop.framework.ProxyFactoryBean">
		<property name="proxyInterfaces" value="com.ipay.ipayment.service.facade.api.StagedPaymentService" />
		<property name="target" ref="stagedPaymentServiceTarget" />
		<property name="interceptorNames">
			<list>
				<value>businessActionInterceptor</value>
			</list>
		</property>
	</bean>

org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator
如：
<bean id="tenantForbidProxyCreator"
		  class="org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator">
		<property name="interceptorNames">
			<list>
				<value>tenantForbidInterceptor</value>
			</list>
		</property>
		<property name="beanNames">
			<list>
				<value>globalUniqueInfoDAO</value>
			</list>
		</property>
	</bean>


#反射工具
org.springframework.util.ReflectionUtils


#注解
org.springframework.core.annotation.AnnotationUtils 


#登录授权认证
net.sf.acegisecurity.GrantedAuthority

#http 
##拦截器
org.springframework.web.servlet.HandlerInterceptor
org.springframework.web.filter.OncePerRequestFilter


#拷贝
利用字节输入输出实现对象深度拷贝
private <T> T deepClone(T object) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            IOUtils.closeQuietly(ois);
            IOUtils.closeQuietly(oos);
        }
    }


#字段验证
org.hibernate.validator.constraints 包下注解使用
javax.validation.constraints 包下注解使用
如：在pojo字段上标注hibernate的NotBlank注解，可以使用javax.validation.Validation进行非空校验
    private final static Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<Object>> constraintViolations = VALIDATOR.validate(object)；

#字符串格式化
java.text.MessageFormat.format()
String.format()


前端唯一转时区的情况，是转成客户端当地时区，因为这个时区只有前端可感知，此时后端给unix timestamp，前端转成当前时区显示
其它情况前端都应该尽量不感知时区，尽量后端转好直出显示。
对于后端给字符串而非timestamp到前端转成Date类型时要特别注意，string -> Date 各浏览器行为差异非常大，非特殊情况应该避免这种设计
