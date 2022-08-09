package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.LoadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Configuration
@Service
public class OpenBankingService {

    private static final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGDNA_LOGGER);

    @Autowired
    LogService logService;

    @Value("${urlOpenbankingservice}")
    private String urlOpenBanking;

    @Autowired
    RestUtilService restUtilService;

    public Object requestBankTransHistory(Integer lndCustomerId) {
        logService.logInfo(logger, "Trans History Retrieve started ");

        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while retrieving BankTransHistory");
            return null;
        } else {
            String url = urlOpenBanking + "/api/core/requestBankTransHistory";
            Object result = restUtilService.callGetWithId(url, lndCustomerId);

            if (result == null) {
                logService.logError(logger, "Unable to retrieve Trans History for Customer : " + lndCustomerId);
            } else {
                logService.logInfo(logger, "Trans History Retrieved for Customer : " + lndCustomerId);
            }

            return result;
        }
    }

    public Object  requestLoanHistory(Integer lndCustomerId) {

        logService.logInfo(logger, "Loans History Retrieve started ");

        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while retrieving LoanHistory");
            return null;
        } else {
            String url = urlOpenBanking + "/api/core/requestLoanHistory";
            Object result = restUtilService.callGetWithId(url, lndCustomerId);

            if (result == null) {
                logService.logError(logger, "Unable to retrieve Loans History for Customer : " + lndCustomerId);
            } else {
                logService.logInfo(logger, "Loans History Retrieved for Customer : " + lndCustomerId);
            }

            return result;
        }
    }
}