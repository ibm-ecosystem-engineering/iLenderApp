package com.ilender.micro.service;

import com.ilender.micro.common.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration

@Service
public class PingService {

    @Autowired
    CoreService coreService;

    public Object pingLoan () {
        LogUtil.logDebug(("PingService  pingLoan Started " ));
        return coreService.loans();
    }

    public Object pingLoanHistory () {
        LogUtil.logDebug(("PingService  pingLoanHistory Started "));
        return coreService.requestLoanHistory(40001);
    }

    public Object pingCustomers () {
        LogUtil.logDebug(("PingService  pingCustomers Started "));
        return coreService.customers();
    }

    public Object pingAllServices (int customerId, int loanId, int loanOfferId) {
        LogUtil.logDebug(("PingService  pingAllServices Started "));
        coreService.loans();
        coreService.loansByCustomerId(customerId);
        coreService.requestBankTransHistory(customerId);
        coreService.requestLoanHistory(customerId);
        coreService.customers();
        coreService.customerProfile(customerId);
        coreService.creditScore(customerId, loanId);
        coreService.loanRequestApproved(loanId);
        coreService.loanOfferAccepted(loanOfferId);
        LogUtil.logDebug(("PingService  pingAllServices completed "));
        return "true";
    }
}