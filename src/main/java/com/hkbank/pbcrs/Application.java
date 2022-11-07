package com.hkbank.pbcrs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletRegistration;

@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = "com.hkbank.pbcrs")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

