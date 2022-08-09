package com.ilender.micro;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
//Note: //@EnableOAuth2Sso : This should not be here. Otherwise the MyWebSecurityConfigurerAdapter will not work.
//@EnableOAuth2Sso
public class FrontWebServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGDNA_LOGGER);

	public static void main(String[] args) {
		SpringApplication.run(FrontWebServiceApplication.class, args);

		LogService logService = new LogService();
		logService.logInfo(logger, "Application Started ...");
	}
//
//    @Bean
//    public WebMvcConfigurer configurer(){
//        return new WebMvcConfigurer(){
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*").allowedHeaders("*").allowedMethods(("*"));
//            }
//        };
//    }

}
