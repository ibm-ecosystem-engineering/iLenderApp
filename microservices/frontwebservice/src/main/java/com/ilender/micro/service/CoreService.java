package com.ilender.micro.service;

import com.ilender.micro.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoreService {

    private static final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGDNA_LOGGER);

    @Autowired
    LogService logService;

    @Value("${urlUserservice}")
    private String urlUser;

    @Value("${urlLoanservice}")
    private String urlLoan;

    @Value("${urlCustomerprofileservice}")
    private String urlCustomerprofile;

    @Value("${urlCreditscoreservice}")
    private String urlCreditscore;

    @Value("${urlLoanprocessorservice}")
    private String urlLoanprocessor;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RestUtilService restUtilService;

    public Object loans() {
        String url = urlLoan + "/api/loan";
        Object result = restUtilService.callHttpClient2(url, Object.class);

        if (result == null) {
            logService.logError(logger, "Unable to retrieve Loans");
        } else {
            logService.logInfo(logger, "Loans Retrieved");
        }
        return result;
    }

    public Object loansByCustomerId(int customerId) {
        String url = urlLoan + "/api/loan/findAllByLndCustomerId/" + customerId;

        Object result = restUtilService.callHttpClient2(url, Object.class);

        if (result == null) {
            logService.logError(logger, "Unable to retrieve Loans for Customer : " + customerId);
        } else {
            logService.logInfo(logger, "Loans Retrieved for Customer : " + customerId);
        }
        return result;
    }

    public Object loansAndHistoryByCustomerId(int customerId) {
        List list = new ArrayList();
        list.add(loansByCustomerId(customerId));
        list.add(requestBankTransHistory(customerId));
        list.add(requestLoanHistory(customerId));
        return list;
    }

    public Object requestBankTransHistory(int customerId) {
        String url = urlCustomerprofile + "/api/core/bankTransHistory/" + customerId;
        Object result =  restUtilService.callHttpClient2(url, Object.class);
        if (result == null) {
            logService.logError(logger, "Unable to retrieve Transaction History for Customer : " + customerId);
        } else {
            logService.logInfo(logger, "Transaction History Retrieved for Customer : " + customerId);
        }
        return result;
    }

    public Object requestLoanHistory(int customerId) {
        String url = urlCustomerprofile + "/api/core/loanHistory/" + customerId;
        Object result =  restUtilService.callHttpClient2(url, Object.class);
        if (result == null) {
            logService.logError(logger, "Unable to retrieve Loan History for Customer : " + customerId);
        } else {
            logService.logInfo(logger, "Loan History Retrieved for Customer : " + customerId);
        }
        return result;
    }

    public Object customers() {
        String url = urlUser + "/api/customer";
        Object result =  restUtilService.callHttpClient2(url, Object.class);
        if (result == null) {
            logService.logError(logger, "Unable to retrieve Customers");
        } else {
            logService.logInfo(logger, "Customers Retrieved");
        }
        return result;

    }

    public Object customerProfile(int customerId) {
        String url = urlUser + "/api/customer/" + customerId;

        Object result =  restUtilService.callHttpClient2(url, Object.class);
        if (result == null) {
            logService.logError(logger, "Unable to retrieve CustomerProfile for Customer : " + customerId);
        } else {
            logService.logInfo(logger, "CustomerProfile Retrieved for Customer : " + customerId);
        }
        return result;
    }

    public Object creditScore(int customerId, int loanId) {
        logService.logInfo(logger, "CreditScore Retrieve started ");

        String url = urlCreditscore + "/api/core/creditScore/" + customerId + "/" + loanId;

        String result =  restUtilService.callGet(url);
        if (StringUtil.isNullOrEmpty(result)) {
            logService.logError(logger, "Unable to retrieve CreditScore for Customer : " + customerId);
        } else {
            logService.logInfo(logger, "CreditScore Retrieved for Customer : " + customerId);
        }

        LoadUtil.processLoad();

        return result;
    }

    public Object loanByPurpose(String purpose) {
        String url = urlLoan + "/api/loan/loanByPurpose/" + purpose;

        String result =  restUtilService.callGet(url);
        if (StringUtil.isNullOrEmpty(result)) {
            logService.logError(logger, "Unable to retrieve loan for Purpose : " + purpose);
        } else {
            logService.logInfo(logger, "Loan Retrieved for Purpose : " + purpose);
        }
        return result;
    }

    public Object loanById(int id) {
        String url = urlLoan + "/api/loan/" + id;

        Object result =  restUtilService.callHttpClient2(url, Object.class);
        if (result == null) {
            logService.logError(logger, "Unable to retrieve loan by Id : " + id);
        } else {
            logService.logInfo(logger, "Loan Retrieved by Id : " + id);
        }
        return result;
    }

    public Object loanRequestApproved(int loanId) {

        logService.logInfo(logger, "Loan approve started ");

        String url = urlLoanprocessor + "/api/core/approveLoanRequest/" + loanId;

        Object result =  restUtilService.callHttpClient2(url, Object.class);
        if (result == null) {
            logService.logError(logger, "Unable to approve loan request for loan Id : " + loanId);
        } else {
            logService.logInfo(logger, "Loan approved for loan Id : " + loanId);
        }
        return result;
    }

    public Object loanRequestRejected(int loanId) {
        String url = urlLoanprocessor + "/api/core/rejectLoanRequest/" + loanId;

        Object result =  restUtilService.callHttpClient2(url, Object.class);
        if (result == null) {
            logService.logError(logger, "Unable to reject loan request for loan Id : " + loanId);
        } else {
            logService.logInfo(logger, "Loan rejected for loan Id : " + loanId);
        }
        return result;
    }

    public Object loanOfferAccepted(int loanOfferId) {
        logService.logInfo(logger, "Accept Loan offer started ");

        String url = urlLoanprocessor + "/api/core/acceptLoanOffer/" + loanOfferId;

        Object result =  restUtilService.callHttpClient2(url, Object.class);
        if (result == null) {
            logService.logError(logger, "Unable to accept loan offer for loan offer Id : " + loanOfferId);
        } else {
            logService.logInfo(logger, "Loan offer accept for loan offer Id : " + loanOfferId);
        }
        return result;
    }

    public Object addNewLoan(Object entity) {
        String url = urlLoan + "/api/loan/" ;

        Object result =  restUtilService.callPost(url, entity);
        if (result == null) {
            logService.logError(logger, "Unable to create new loan");
        } else {
            logService.logInfo(logger, "New loan created");
        }
        return result;
    }

}
