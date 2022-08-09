package com.ilender.micro.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    LogService logService;

    @Value("${urlUserservice}")
    private String urlUser;

    @Autowired
    RestUtilService restUtilService;

    public Object processLogin(Object entity) {
        String url = urlUser + "/public/login";

        Object result = restUtilService.callPost(url, entity);
        if (result == null) {
            logService.logError(logger, "Unable to process login");
        }
        return result;
    }

}
