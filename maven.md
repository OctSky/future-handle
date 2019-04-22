#打包上传脚本

#!/usr/bin/env bash
# usage: 需要先在setting.xml中加入以下内容

#<servers>
#  <server>
#       这里的id对应下面的repositoryId
#      <id>nexus</id>
#      <username>username</username>
#      <password>password</password>
#   </server>
#</servers>

export version=`mvn help:evaluate -Dexpression=project.version|grep -Ev '(^\[|Download\w+:)'`
export artifactId=`mvn help:evaluate -Dexpression=project.artifactId|grep -Ev '(^\[|Download\w+:)'`
export groupId=`mvn help:evaluate -Dexpression=project.groupId|grep -Ev '(^\[|Download\w+:)'`
mvn clean install deploy:deploy-file \
    -Dmaven.test.skip=true \
    -DgroupId=${groupId} \
    -DartifactId=${artifactId} \
    -Dversion=${version} \
    -DgeneratePom=true \
    -Dpackaging=jar \
    -DrepositoryId=nexus \
    -Durl=http://mvn.dev.alipay.net/artifactory/content/repositories/Alipay-Releases-intl \
    -Dfile=target/${artifactId}-${version}.jar \
    -Dsource=target/${artifactId}-${version}-sources.jar




#maven命令
mvn[plugin-name]:[goal-name]，即执行插件的目标动作
有两种调用方式：
1、将插件目标与生命周期阶段绑定，使用时只输入命令周期阶段，如 mvn compile 实际先定位到 compile 生命周期阶段，然后根据绑定关系调用 maven-compile-plugin 的 compile 目标
2、直接在命令行按照上面格式指定要执行的目标插件，如mvn archetype:generate 表示调用 maven-archetype-plugin 的genarate目标，与生命周期阶段无关。

** 常见 maven 命令如下 **
mvn -v					显示maven版本信息
mvn clean				清理项目产生的临时文件，一般是target目录
mvn compile 			编译源代码，一般编译src/main/java目录下的代码
mvn package				项目打包，会在项目模块下的target目录生成jar或war文件
mvn test				测试命令，或执行src/test/java下的junit测试用例
mvn install				将编译打包的jar/war文件发布到本地仓库，供其他模块或项目使用
mvn deploy				将jar/war文件发布到远程仓库
mvn site				生成项目相关信息的网站
mvn dependency:tree 	打印出项目的整个依赖树
mvn archetype:generate	创建maven的普通java项目
mvn tomcat:run			在tomcat容器中运行web应用

** maven命令可携带的参数 **
-D 传入属性参数
	如 mvn pacakge -Dmaven.test.skip=true
-e 显示maven运行出错的信息
-o 离线执行命令，即不去远程仓库更新包
-X 显示maven允许的debug信息
-U 强制远程更新snapshot的的插件或依赖。默认每天只更新一次。
-P 使用指定的profile配置
	项目开发可能有多个环境，比如开发、测试、预发、正式等，在pom中配置如：
	<profiles>  
	      <profile>  
	             <id>dev</id>  
	             <properties>  
	                    <env>dev</env>  
	             </properties>  
	             <activation>  
	                    <activeByDefault>true</activeByDefault>  
	             </activation>  
	      </profile>  
	      <profile>  
	             <id>qa</id>  
	             <properties>  
	                    <env>qa</env>  
	             </properties>  
	      </profile>  
	      <profile>  
	             <id>pre</id>  
	             <properties>  
	                    <env>pre</env>  
	             </properties>  
	      </profile>  
	      <profile>  
	             <id>prod</id>  
	             <properties>  
	                    <env>prod</env>  
	             </properties>  
	      </profile>  
	</profiles>  
	   
	......  
	   
	<build>  
	      <filters>  
	             <filter>config/${env}.properties</filter>  
	      </filters>  
	      <resources>  
	             <resource>  
	                    <directory>src/main/resources</directory>  
	                    <filtering>true</filtering>  
	             </resource>  
	      </resources>  
	   
	      ......  
	   
	</build>  
	profiles定义了各个环境的变量id，filters中定义了变量配置文件的地址，其中地址中的环境变量就是上面profile中定义的值，resources中是定义哪些目录下的文件会被配置文件中定义的变量替换。
	如 mvn package -P dev