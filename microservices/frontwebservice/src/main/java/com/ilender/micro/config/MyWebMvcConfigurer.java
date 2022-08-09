package com.ilender.micro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

//        registry
//                .addMapping("*")
//                .allowedMethods("OPTIONS", "GET", "PUT", "POST", "DELETE")
//                .allowedOrigins("*")
//                .allowedHeaders("*");
//

        registry.addMapping("/**")
                .allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
    }
}