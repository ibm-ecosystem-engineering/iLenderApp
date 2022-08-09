package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.ConversionUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.model.LoanInfo;
import com.ilender.micro.model.LoanOfferInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
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

    public void updateLoanRequestApproved(Integer lndLoanId) {
        String url = urlLoanservice + "/api/core/updateLoanRequestApproved";
        Object result = restUtilService.callGetWithId(url, lndLoanId);
        if (result == null) {
            logService.logError(logger, "Unable to update LoanRequestApproved status for loan : " + lndLoanId);
        } else {
            logService.logInfo(logger, "LoanRequestApproved status updated for loan : " + lndLoanId);
        }
    }
    public void updateLoanRequestRejected(Integer lndLoanId) {
        String url = urlLoanservice + "/api/core/updateLoanRequestRejected";
        Object result = restUtilService.callGetWithId(url, lndLoanId);
        if (result == null) {
            logService.logError(logger, "Unable to update LoanRequestRejected status for loan : " + lndLoanId);
        } else {
            logService.logInfo(logger, "LoanRequestRejected status updated for loan : " + lndLoanId);
        }
    }
    public void updateLoanOfferAccepted(Integer lndLoanOfferId) {
        String url = urlLoanservice + "/api/core/updateLoanOfferAccepted";
        Object result = restUtilService.callGetWithId(url, lndLoanOfferId);
        if (result == null) {
            logService.logError(logger, "Unable to update LoanOfferAccepted status for loan offer : " + lndLoanOfferId);
        } else {
            logService.logInfo(logger, "LoanOfferAccepted status updated for loan offer : " + lndLoanOfferId);
        }
    }


    public LoanOfferInfo getLoanOfferInfo(Integer lndLoanOfferId) {
        String url = urlLoanservice + "/api/loanOffer/";
        String jsonString = (String) restUtilService.callGetWithId(url, lndLoanOfferId);
        if (jsonString == null) {
            logService.logError(logger, "Unable to retrieve LoanOfferInfo for loan offer : " + lndLoanOfferId);
        } else {
            logService.logInfo(logger, "LoanOfferInfo Retrieved for loan offer : " + lndLoanOfferId);
        }
        LoanOfferInfo result =  (LoanOfferInfo)  ConversionUtil.jsonToObject(jsonString, LoanOfferInfo.class);
        LogUtil.logDebug("KafaProcessor : getLoanOfferInfo : " + result);
        return result;
    }

    public LoanInfo getLoanInfo(Integer lndLoanId) {
        String url = urlLoanservice + "/api/loan/";
        String jsonString = (String) restUtilService.callGetWithId(url, lndLoanId);
        if (jsonString == null) {
            logService.logError(logger, "Unable to retrieve Loan : " + lndLoanId);
        } else {
            logService.logInfo(logger, "Loan Retrieved : " + lndLoanId);
        }
        LoanInfo result =  (LoanInfo) ConversionUtil.jsonToObject(jsonString, LoanInfo.class);
        LogUtil.logDebug("KafaProcessor : getLoanInfo : " + result);
        return result;
    }

}