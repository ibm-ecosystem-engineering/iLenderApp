package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.model.LoanDetailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    //Update LoanOffer from Bank in LndLoanOffer table
    public void updateLoanOfferInDB(List loanOfferList) {
        String url2 = urlLoanservice + "/api/core/updateOfferFromBank";
        Object result = restUtilService.callPost(url2, loanOfferList);

        if (result == null) {
            logService.logError(logger, "Unable to Update LoanOffer");
        } else {
            logService.logInfo(logger, "Update LoanOffer success");
        }
    }

    //Update LoanDetail from Bank in LndLoanDetail table
    public void updateLoanDetailInDB(LoanDetailInfo loanDetailInfo) {
        String url2 = urlLoanservice + "/api/core/updateDetailFromBank";
        Object result = restUtilService.callPost(url2, loanDetailInfo);
        if (result == null) {
            logService.logError(logger, "Unable to Update LoanDetail");
        } else {
            logService.logInfo(logger, "Update LoanDetail success");
        }
    }

}