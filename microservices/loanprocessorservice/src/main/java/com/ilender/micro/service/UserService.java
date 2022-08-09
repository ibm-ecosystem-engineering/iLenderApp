package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.ConversionUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.model.CustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Configuration
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGDNA_LOGGER);

    @Autowired
    LogService logService;

    @Value("${urlUserservice}")
    private String urlUserservice;

    @Autowired
    RestUtilService restUtilService;

    public CustomerInfo getCustomerInfo(Integer lndCustomerId) {
        String url = urlUserservice + "/api/customer/";
        String jsonString = (String) restUtilService.callGetWithId(url, lndCustomerId);
        if (jsonString == null) {
            logService.logError(logger, "Unable to retrieve Customer : " + lndCustomerId);
        } else {
            logService.logInfo(logger, "Customer Retrieved : " + lndCustomerId);
        }
        CustomerInfo result =  (CustomerInfo)  ConversionUtil.jsonToObject(jsonString, CustomerInfo.class);
        LogUtil.logDebug("UserService : getCustomerInfo : " + result);
        return result;
    }

}