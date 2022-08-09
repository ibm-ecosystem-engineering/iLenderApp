package com.ilender.micro.controller;

import com.ilender.micro.service.CoreService;
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

    @GetMapping("/acceptLoanOffer/{lndLoanOfferId}")
    @ResponseBody
    private Object acceptLoanOffer (@PathVariable("lndLoanOfferId") Integer lndLoanOfferId) {
        return service.acceptLoanOffer (lndLoanOfferId);
    }

    @GetMapping("/approveLoanRequest/{lndLoanId}")
    @ResponseBody
    private Object approveLoanRequest (@PathVariable("lndLoanId") Integer lndLoanId) {
        return service.approveLoanRequest (lndLoanId);
    }

    @GetMapping("/rejectLoanRequest/{lndLoanId}")
    @ResponseBody
    private Object rejectLoanRequest (@PathVariable("lndLoanId") Integer lndLoanId) {
        return service.rejectLoanRequest (lndLoanId);
    }

}
