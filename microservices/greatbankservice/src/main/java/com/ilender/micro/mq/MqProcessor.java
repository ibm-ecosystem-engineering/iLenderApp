package com.ilender.micro.mq;

import com.ilender.micro.model.*;
import com.ilender.micro.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class MqProcessor {

    @Autowired
    CoreService service;

    @Autowired
    LndBankTransHistoryService lndBankTransHistoryService;

    @Autowired
    LndLoanHistoryService lndLoanHistoryService;

    @Autowired
    MQSender mqSender;

    public void loadLoanOffer(LoanOfferRequestInfo entity) {
        Object result = service.loadLoanOffer(entity);
        mqSender.sendMessageRequestLoanOffer(result);
    }

    public void loadLoanDetail(LoanDetailRequestInfo entity) {
        Object result = service.loadLoanDetail(entity);
        mqSender.sendMessageRequestLoanDetail(result);
    }

    public void bankTransHistory(BankTransHistoryRequestInfo entity) {
        Object result = lndBankTransHistoryService.findAllByLndCustomerId(entity.getCustomerId());
        mqSender.sendMessageRequestTransHistory(result);
    }

    public void loanHistory(LoanHistoryRequestInfo entity) {
        Object result = lndLoanHistoryService.findAllByLndCustomerId(entity.getCustomerId());
        mqSender.sendMessageRequestLoanHistory(result);
    }
}