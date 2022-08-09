package com.ilender.micro.controller;

import com.ilender.micro.common.LoadUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.entity.LndLoan;
import com.ilender.micro.model.LoanDetailInfo;
import com.ilender.micro.model.LoanOfferInfo;
import com.ilender.micro.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/core")
public class CoreController {

    @Autowired
    CoreService service;

    @CrossOrigin
    @GetMapping("/updateLoanRequestApproved/{lndLoanId}")
    @ResponseBody
    private LndLoan updateLoanRequestApproved (@PathVariable("lndLoanId") int lndLoanId) {
        LogUtil.logDebug("CoreController 1updateLoanRequestApproved ... " + lndLoanId);

        LoadUtil.processLoad();
        return service.updateLoanRequestApproved(lndLoanId);
    }

    @CrossOrigin
    @GetMapping("/updateLoanRequestRejected/{lndLoanId}")
    @ResponseBody
    private LndLoan updateLoanRequestRejected (@PathVariable("lndLoanId") int lndLoanId) {
        LogUtil.logDebug("CoreController updateLoanRequestRejected ... " + lndLoanId);

        LoadUtil.processLoad();
        return service.updateLoanRequestRejected(lndLoanId);
    }

    @CrossOrigin
    @GetMapping("/updateLoanOfferAccepted/{lndLoanOfferId}")
    @ResponseBody
    private LndLoan updateLoanOfferAccepted (@PathVariable("lndLoanOfferId") int lndLoanOfferId) {
        LogUtil.logDebug("CoreController 2updateLoanOfferAccepted ... " + lndLoanOfferId);

        LoadUtil.processLoad();
        return service.updateLoanOfferAccepted(lndLoanOfferId);
    }

    @CrossOrigin
    @GetMapping("/updateCreditScore/{lndLoanId}/{creditScore}")
    @ResponseBody
    private LndLoan updateCreditScore (@PathVariable("lndLoanId") int lndLoanId, @PathVariable("creditScore") double creditScore) {
        LogUtil.logDebug("CoreController updateCreditScore ... " + lndLoanId);
        LogUtil.logDebug("CoreController updateCreditScore ... " + creditScore);


        LoadUtil.processLoad();
        return service.updateCreditScore(lndLoanId, creditScore);
    }

    @CrossOrigin
    @PostMapping("/updateOfferFromBank")
    private Boolean updateOfferFromBank(@RequestBody List<LoanOfferInfo> list) {
        LogUtil.logDebug("CoreController 3updateOfferFromBank ... " + list);

        LoadUtil.processLoad();
        return service.updateOfferFromBank(list);
    }

    @CrossOrigin
    @PostMapping("/updateDetailFromBank")
    private Boolean updateDetailFromBank(@RequestBody LoanDetailInfo info) {
        LogUtil.logDebug("CoreController 4updateDetailFromBank ... " + info);

        LoadUtil.processLoad();
        return service.updateDetailFromBank(info);
    }

}
