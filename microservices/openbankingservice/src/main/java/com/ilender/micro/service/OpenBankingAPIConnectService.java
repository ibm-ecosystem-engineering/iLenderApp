package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.ConversionUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.model.*;
import com.ilender.micro.util.OpenBankingDataConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Service
public class OpenBankingAPIConnectService {

    @Autowired
    AuthCallService authCallService;

    public Object requestBankTransHistory(Integer lndCustomerId) {
        List list1 = callGetTransHistory(CommonConstants.BANK_BIG, CommonConstants.BANK_TRANS_HISTORY, lndCustomerId);
        List list2 = callGetTransHistory(CommonConstants.BANK_GREAT, CommonConstants.BANK_TRANS_HISTORY, lndCustomerId);
        List<LndBankTransHistory> list = mergeList(list1, list2);
        return list;
    }

    public Object requestLoanHistory(Integer lndCustomerId) {
        LoanHistoryRequestInfo info = new LoanHistoryRequestInfo(lndCustomerId);
        List list1 = callPost(CommonConstants.BANK_BIG, CommonConstants.BANK_LOAN_HISTORY, info);
        List list2 = callPost(CommonConstants.BANK_GREAT, CommonConstants.BANK_LOAN_HISTORY, info);
        List list = mergeList(list1, list2);
        return list;
    }

    public List requestLoanOffer(int customerId, int loanId, double loanAmount) {
        LoanOfferRequestInfo info = new LoanOfferRequestInfo(customerId, loanId, loanAmount);
        List list1 = callPost(CommonConstants.BANK_BIG, CommonConstants.BANK_LOAN_OFFER, info);
        List list2 = callPost(CommonConstants.BANK_GREAT, CommonConstants.BANK_LOAN_OFFER, info);
        List list = mergeList(list1, list2);
        return list;
    }

    public LoanDetailInfo requestLoanDetail(int bankId, int customerId, int loanId, double loanAmount) {
        LoanDetailRequestInfo info = new LoanDetailRequestInfo(customerId, loanId, loanAmount);
        String jsonString =  authCallService.callPost(bankId, CommonConstants.BANK_LOAN_DETAIL, info);
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

    private List callGetTransHistory(int bankId, int apiId, int id) {
        LogUtil.logDebug("OpenBankingAPIConnectService :callGetTransHistory : bankId : " + bankId + ": apiId : " + apiId + ": id : " + id);

        String jsonString =  authCallService.callGetForOpenBanking(bankId, apiId, id);
        LogUtil.logDebug("OpenBankingAPIConnectService :callGetTransHistory : jsonString : " + jsonString);

        List list = OpenBankingDataConverterUtil.convertTransHistory(jsonString, bankId);

        LogUtil.logDebug("OpenBankingAPIConnectService :callGetTransHistory : list ");
        return list;
    }

    private List callPost(int bankId, int apiId, Object inputInfo) {
        LogUtil.logDebug("OpenBankingAPIConnectService :callPost : bankId : " + bankId + ": apiId : " + apiId + ": inputInfo : " + inputInfo);

        String jsonString =   authCallService.callPost(bankId, apiId, inputInfo);
        LogUtil.logDebug("OpenBankingAPIConnectService :callPost : jsonString : " + jsonString);

        List list =  (List) ConversionUtil.jsonToObject(jsonString, List.class);

        LogUtil.logDebug("OpenBankingAPIConnectService :callPost : list ");
        return list;
    }

}