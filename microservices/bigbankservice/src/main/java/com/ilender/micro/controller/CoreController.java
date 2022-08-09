package com.ilender.micro.controller;

import com.ilender.micro.common.LogUtil;
import com.ilender.micro.model.BankTransHistoryRequestInfo;
import com.ilender.micro.model.LoanDetailRequestInfo;
import com.ilender.micro.model.LoanHistoryRequestInfo;
import com.ilender.micro.model.LoanOfferRequestInfo;
import com.ilender.micro.service.CoreService;
import com.ilender.micro.service.LndBankTransHistoryService;
import com.ilender.micro.service.LndLoanHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

@Configuration
@CrossOrigin
@RestController
@RequestMapping("/api/core")
public class CoreController {

    @Autowired
    CoreService service;

    @Autowired
    LndBankTransHistoryService lndBankTransHistoryService;

    @Autowired
    LndLoanHistoryService lndLoanHistoryService;

    @CrossOrigin
    @PostMapping("/loadLoanOffer")
    private Object loadLoanOffer(@RequestBody LoanOfferRequestInfo entity) {
        return service.loadLoanOffer(entity);
    }

    @CrossOrigin
    @PostMapping("/loadLoanDetail")
    private Object loadLoanDetail(@RequestBody LoanDetailRequestInfo entity) {
        return service.loadLoanDetail(entity);
    }

    @CrossOrigin
    @PostMapping("/bankTransHistory")
    private Object bankTransHistory(@RequestBody BankTransHistoryRequestInfo entity) {
        return lndBankTransHistoryService.findAllByLndCustomerId(entity.getCustomerId());
    }

    @CrossOrigin
    @PostMapping("/loanHistory")
    private Object loanHistory(@RequestBody LoanHistoryRequestInfo entity) {
        return lndLoanHistoryService.findAllByLndCustomerId(entity.getCustomerId());
    }

}
