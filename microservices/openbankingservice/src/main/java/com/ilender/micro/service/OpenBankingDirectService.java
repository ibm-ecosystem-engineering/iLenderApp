package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.ConversionUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Configuration
@Service
public class OpenBankingDirectService {

    private static final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGDNA_LOGGER);

    @Autowired
    LogService logService;

    @Value("${urlBigbankservice}")
    private String urlBigbankservice;

    @Value("${urlGreatbankservice}")
    private String urlGreatbankservice;

    @Autowired
    RestUtilService restUtilService;

    public Object requestBankTransHistory(Integer lndCustomerId) {
        BankTransHistoryRequestInfo info = new BankTransHistoryRequestInfo(lndCustomerId);

        List list1 = callPost(info, urlBigbankservice + "/api/core/bankTransHistory");
        if (list1 == null) {
            logService.logError(logger, "Unable to retrieve BigBank TransHistory for customer : " + lndCustomerId);
        } else {
            logService.logInfo(logger, "BigBank TransHistory Retrieved for customer : " + lndCustomerId);
        }

        List list2 = callPost(info, urlGreatbankservice + "/api/core/bankTransHistory");
        if (list2 == null) {
            logService.logError(logger, "Unable to retrieve GreatBank TransHistory for customer : " + lndCustomerId);
        } else {
            logService.logInfo(logger, "GreatBank TransHistory Retrieved for customer : " + lndCustomerId);
        }

        List<LndBankTransHistory> list = mergeList(list1, list2);
        return list;
    }

    public Object requestLoanHistory(Integer lndCustomerId) {
        LoanHistoryRequestInfo info = new LoanHistoryRequestInfo(lndCustomerId);
        List list1 = callPost(info, urlBigbankservice + "/api/core/loanHistory");
        if (list1 == null) {
            logService.logError(logger, "Unable to retrieve BigBank LoanHistory for customer : " + lndCustomerId);
        } else {
            logService.logInfo(logger, "BigBank LoanHistory Retrieved for customer : " + lndCustomerId);
        }

        List list2 = callPost(info, urlGreatbankservice + "/api/core/loanHistory");
        if (list2 == null) {
            logService.logError(logger, "Unable to retrieve GreatBank LoanHistory for customer : " + lndCustomerId);
        } else {
            logService.logInfo(logger, "GreatBank LoanHistory Retrieved for customer : " + lndCustomerId);
        }

        List list = mergeList(list1, list2);
        return list;
    }

    public List requestLoanOffer(int customerId, int loanId, double loanAmount) {
        LoanOfferRequestInfo info = new LoanOfferRequestInfo(customerId, loanId, loanAmount);
        List list1 = callPost(info, urlBigbankservice + "/api/core/loadLoanOffer");
        if (list1 == null) {
            logService.logError(logger, "Unable to retrieve BigBank LoanOffer for customer : " + customerId);
        } else {
            logService.logInfo(logger, "BigBank LoanOffer Retrieved for customer : " + customerId);
        }

        List list2 = callPost(info, urlGreatbankservice + "/api/core/loadLoanOffer");
        if (list2 == null) {
            logService.logError(logger, "Unable to retrieve GreatBank LoanOffer for customer : " + customerId);
        } else {
            logService.logInfo(logger, "GreatBank LoanOffer Retrieved for customer : " + customerId);
        }

        List list = mergeList(list1, list2);
        return list;
    }

    public LoanDetailInfo requestLoanDetail(int bankId, int customerId, int loanId, double loanAmount) {

        LoanDetailRequestInfo info = new LoanDetailRequestInfo(customerId, loanId, loanAmount);

        String url = null;
        if (bankId == 1) {
            url = urlBigbankservice + "/api/core/loadLoanDetail";
        } else {
            url = urlGreatbankservice + "/api/core/loadLoanDetail";
        }

        String jsonString =  (String) restUtilService.callPost(url, info);
        if (jsonString == null) {
            logService.logError(logger, "Unable to retrieve LoanDetail for customer : " + customerId);
        } else {
            logService.logInfo(logger, "LoanDetail Retrieved for customer : " + customerId);
        }

        LoanDetailInfo result  = (LoanDetailInfo) ConversionUtil.jsonToObject(jsonString, LoanDetailInfo.class);

        return result;
    }

    private List mergeList(List list1, List list2) {
        List list = new ArrayList();
        if (list1 != null) {
            list.addAll(list1);
        }
        if (list2 != null) {
            list.addAll(list2);
        }
        return list;
    }


    private List callPost(Object info, String url1) {
        LogUtil.logDebug("OpenBankingDirectService :callPost : callPost : url1 : " + url1);
        LogUtil.logDebug("OpenBankingDirectService :callPost : callPost : info : " + info);

        String jsonString = (String) restUtilService.callPost(url1, info);

        return (List) ConversionUtil.jsonToObject(jsonString , List.class);
    }

}