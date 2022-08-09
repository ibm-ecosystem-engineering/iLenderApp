package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.ConversionUtil;
import com.ilender.micro.common.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Configuration
@Service
public class CustomerProfileService {

    private static final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGDNA_LOGGER);

    @Autowired
    LogService logService;

    @Value("${urlCustomerprofileservice}")
    private String urlCustomerprofile;

    @Autowired
    RestUtilService restUtilService;

    public List fetchLoanHistory(Integer lndCustomerId) {
        logService.logInfo(logger, "Loans History Retrieve started ");

        String url = urlCustomerprofile + "/api/core/loanHistory/";
        LogUtil.logDebug("CoreService : fetchLoanHistory : url : " + url);

        String jsonString = (String) restUtilService.callGetWithId(url, lndCustomerId);
        List list = (List) ConversionUtil.jsonToObject(jsonString, List.class);
        LogUtil.logDebug("CoreService : fetchLoanHistory : object : " + list);

        if (list == null) {
            logService.logError(logger, "Unable to retrieve Loans History for Customer : " + lndCustomerId);
        } else {
            logService.logInfo(logger, "Loans History Retrieved for Customer : " + lndCustomerId);
        }

        return list;
    }

    public List fetchTransHistory(Integer lndCustomerId) {

        logService.logInfo(logger, "Trans History Retrieve started ");

        String url = urlCustomerprofile + "/api/core/bankTransHistory/";
        LogUtil.logDebug("CoreService : fetchTransHistory : url : " + url);

        String jsonString = (String) restUtilService.callGetWithId(url, lndCustomerId);
        List list = (List) ConversionUtil.jsonToObject(jsonString, List.class);
        LogUtil.logDebug("CoreService : fetchTransHistory : object : " + list);

        if (list == null) {
            logService.logError(logger, "Unable to retrieve Trans History for Customer : " + lndCustomerId);
        } else {
            logService.logInfo(logger, "Trans History Retrieved for Customer : " + lndCustomerId);
        }

        return list;
    }
}