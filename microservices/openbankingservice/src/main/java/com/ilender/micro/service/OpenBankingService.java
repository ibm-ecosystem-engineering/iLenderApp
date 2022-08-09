package com.ilender.micro.service;

import com.ilender.micro.common.LogUtil;
import com.ilender.micro.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Configuration
@Service
public class OpenBankingService {

    @Value("${iLenderApiConnectEnabled}")
    private boolean iLenderApiConnectEnabled;

    @Autowired
    OpenBankingDirectService directService;

    @Autowired
    OpenBankingAPIConnectService aPIConnectService;

    public Object requestBankTransHistory(Integer lndCustomerId) {

        LogUtil.logDebug("OpenBankingService : requestBankTransHistory : iLenderApiConnectEnabled : " + iLenderApiConnectEnabled);

        Object result = null;
        if (iLenderApiConnectEnabled) {
            result = aPIConnectService.requestBankTransHistory(lndCustomerId);
        } else {
            result = directService.requestBankTransHistory(lndCustomerId);
        }
        return result;
    }

    public Object requestLoanHistory(Integer lndCustomerId) {
        LogUtil.logDebug("OpenBankingService : requestLoanHistory : iLenderApiConnectEnabled : " + iLenderApiConnectEnabled);

        Object result = null;
        if (iLenderApiConnectEnabled) {
            result = aPIConnectService.requestLoanHistory(lndCustomerId);
        } else {
            result = directService.requestLoanHistory(lndCustomerId);
        }
        return result;
    }

    public List requestLoanOffer(int customerId, int loanId, double loanAmount) {
        List result = null;
        if (iLenderApiConnectEnabled) {
            result = aPIConnectService.requestLoanOffer(customerId, loanId, loanAmount);
        } else {
            result = directService.requestLoanOffer(customerId, loanId, loanAmount);
        }
        return result;
    }

    public LoanDetailInfo requestLoanDetail(int bankId, int customerId, int loanId, double loanAmount) {
        LoanDetailInfo result = null;
        if (iLenderApiConnectEnabled) {
            result = aPIConnectService.requestLoanDetail(bankId, customerId, loanId, loanAmount);
        } else {
            result = directService.requestLoanDetail(bankId, customerId, loanId, loanAmount);
        }
        return result;
    }
}