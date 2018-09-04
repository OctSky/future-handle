package com.github.work;

/**
 * 等价于配置web.xml
 *
 * @author zhangjh
 */
//public class WebStarter implements WebApplicationInitializer {
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        //设定spring根容器
//        servletContext.setInitParameter("contextConfigLocation", "classpath:applicationContext.xml");
//        servletContext.addListener(new ContextLoaderListener());
//
//        //设定spring mvc 分发控制器
//        ServletRegistration.Dynamic registration = servletContext.addServlet(/**name*/"future", new DispatcherServlet());
//        registration.setLoadOnStartup(1);
//        registration.addMapping("/*");
//        registration.setInitParameter("contextConfigLocation", "classpath:future-servlet.xml");
//        /**
//         * 可以基于java配置取代future-servlet.xml，如：
//         *
//         */
//
//    }
//
//    @Configuration
//    @ComponentScan(basePackages = " ")
//    @PropertySource("classpath:applicationContext.properties")
//    @EnableWebMvc
//    public class FutureServletConfig {
//        @Bean
//        public InternalResourceViewResolver internalResourceViewResolver() {
//            InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//            viewResolver.setPrefix("/WEB-INF/jsp/");
//            viewResolver.setSuffix(".jsp");
//            return viewResolver;
//        }
//    }
//}
