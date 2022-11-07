package com.hkbank.pbcrs.config;

import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //setUseSuffixPatternMatch 后缀模式匹配
        configurer.setUseSuffixPatternMatch(true);
        //setUseTrailingSlashMatch 自动后缀路径模式匹配
        configurer.setUseTrailingSlashMatch(true);

    }

    /**
     * 解决tomcat报文件找不到错误
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> customizer() {
        System.out.println("初始化配置加载webapp配置！！！！！");
        return (factory) -> {
            factory.addContextCustomizers((context) -> {
                ((StandardJarScanner)context.getJarScanner()).setScanManifest(false);
            });
        };
    }

//    @Bean
//    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet){
//        ServletRegistrationBean<DispatcherServlet> dispatcherServletServletRegistrationBean = new ServletRegistrationBean<>(dispatcherServlet);
//        dispatcherServletServletRegistrationBean.addUrlMappings("*.html");
//        return dispatcherServletServletRegistrationBean;
//    }
}
