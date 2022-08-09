package com.ilender.micro;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.service.LogService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@SpringBootApplication
public class LoanProcessorServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGDNA_LOGGER);

	public static void main(String[] args) {
		SpringApplication.run(LoanProcessorServiceApplication.class, args);
		LogService logService = new LogService();
		logService.logInfo(logger, "Application Started ...");

	}

}
