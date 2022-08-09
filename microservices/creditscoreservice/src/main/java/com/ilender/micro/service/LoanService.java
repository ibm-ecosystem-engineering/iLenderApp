package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


@Configuration
@Service
public class LoanService {

    private static final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGDNA_LOGGER);

    @Autowired
    LogService logService;

    @Value("${urlLoanservice}")
    private String urlLoanservice;

    @Autowired
    RestUtilService restUtilService;

    public void updateCreditScore(Integer lndLoanId, double creditScore) {

        logService.logInfo(logger, "CreditScore Update started ");

        String url = urlLoanservice + "/api/core/updateCreditScore/" + lndLoanId + "/" + creditScore;
        Object result = restUtilService.callGet(url);

        if (result == null) {
            logService.logError(logger, "Unable to update CreditScore for lndLoanId : " + lndLoanId);
        } else {
            logService.logInfo(logger, "CreditScore Updated for lndLoanId : " + lndLoanId);
        }

    }

}