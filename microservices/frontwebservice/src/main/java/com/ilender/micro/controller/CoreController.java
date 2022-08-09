package com.ilender.micro.controller;

import com.ilender.micro.common.ControllerUtil;
import com.ilender.micro.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/core")
public class CoreController {

    @Autowired
    CoreService service;

    @CrossOrigin
    @GetMapping("/loans")
    @ResponseBody
    private Object loans () {
        return service.loans();
    }

    @CrossOrigin
    @GetMapping("/loanById/{id}")
    @ResponseBody
    private Object loanById (@PathVariable("id") int id) {
        return service.loanById(id);
    }
    @CrossOrigin
    @GetMapping("/loansByCustomerId/{customerId}")
    @ResponseBody
    private Object loansByCustomerId (@PathVariable("customerId") int customerId) {
        return service.loansByCustomerId(customerId);
    }

    @CrossOrigin
    @GetMapping("/loansAndHistoryByCustomerId/{customerId}")
    @ResponseBody
    private Object loansAndHistoryByCustomerId (@PathVariable("customerId") int customerId) {
        return service.loansAndHistoryByCustomerId(customerId);
    }

    @CrossOrigin
    @GetMapping("/requestBankTransHistory/{customerId}")
    @ResponseBody
    private Object requestBankTransHistory (@PathVariable("customerId") int customerId) {
        return service.requestBankTransHistory(customerId);
    }

    @CrossOrigin
    @GetMapping("/requestLoanHistory/{customerId}")
    @ResponseBody
    private Object requestLoanHistory (@PathVariable("customerId") int customerId) {
        return service.requestLoanHistory(customerId);
    }

    @CrossOrigin
    @GetMapping("/customers")
    @ResponseBody
    private Object customers () {
        return service.customers();
    }

    @CrossOrigin
    @GetMapping("/customerProfile/{customerId}")
    @ResponseBody
    private Object customerProfile (@PathVariable("customerId") int customerId) {
        return service.customerProfile(customerId);
    }

    @PostMapping("/addNewLoan/{customerId}")
    private ResponseEntity<?>  addNewLoan(@RequestBody Object entity) {
        Object createResult = service.addNewLoan(entity);
        return ControllerUtil.getResponseEntityForCreate(createResult);
    }

    @CrossOrigin
    @GetMapping("/creditScore/{customerId}/{loanId}")
    @ResponseBody
    private Object creditScore (@PathVariable("customerId") int customerId, @PathVariable("loanId") int loanId) {
        return service.creditScore(customerId, loanId);
    }

    @CrossOrigin
    @GetMapping("/loanByPurpose/{purpose}")
    @ResponseBody
    private Object loanByPurpose (@PathVariable("purpose") String purpose) {
        return service.loanByPurpose(purpose);
    }

    @CrossOrigin
    @GetMapping("/loanRequestApproved/{loanId}")
    @ResponseBody
    private Object loanRequestApproved (@PathVariable("loanId") int loanId) {
        return service.loanRequestApproved(loanId);
    }

    @CrossOrigin
    @GetMapping("/loanRequestRejected/{loanId}")
    @ResponseBody
    private Object loanRequestRejected (@PathVariable("loanId") int loanId) {
        return service.loanRequestRejected(loanId);
    }

    @CrossOrigin
    @GetMapping("/loanOfferAccepted/{loanOfferId}")
    @ResponseBody
    private Object loanOfferAccepted (@PathVariable("loanOfferId") int loanOfferId) {
        return service.loanOfferAccepted(loanOfferId);
    }

}
