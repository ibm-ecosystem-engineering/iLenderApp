package com.ilender.micro.config;

import com.ilender.micro.service.LogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public LogService logService() {
//        return new LogService();
//    }

//    @Bean
//    public LoadService loadService() {
//        LoadService loadService = new LoadService();
//        loadService.loadOnStartup();
//        return loadService;
//    }

}
