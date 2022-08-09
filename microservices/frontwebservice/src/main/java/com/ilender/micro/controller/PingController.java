package com.ilender.micro.controller;

import com.ilender.micro.common.LoadUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.service.LoadService;
import com.ilender.micro.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;


@Configuration
@CrossOrigin
@RestController
public class PingController {

    @Autowired
    PingService pingService;

    @Autowired
    LoadService service;

    @CrossOrigin
    @GetMapping("pingLoan")
    @ResponseBody
    private Object pingLoan () {
        LogUtil.logDebug(("PingController  pingLoan Started "));
        return pingService.pingLoan();
    }

    @CrossOrigin
    @GetMapping("pingLoanHistory")
    @ResponseBody
    private Object pingLoanHistory () {
        LogUtil.logDebug(("PingController  pingLoanHistory Started "));
        return pingService.pingLoanHistory();
    }

    @CrossOrigin
    @GetMapping("pingCustomers")
    @ResponseBody
    private Object pingCustomers () {
        LogUtil.logDebug(("PingController  pingCustomers Started "));
        return pingService.pingCustomers();
    }

    @CrossOrigin
    @GetMapping("/pingAllServices/{customerId}/{loanId}/{loanOfferId}")
    @ResponseBody
    private Object pingAllServices (@PathVariable("customerId") int customerId, @PathVariable("loanId") int loanId, @PathVariable("loanOfferId") int loanOfferId) {
        LogUtil.logDebug("LoadController pingAllServices ... ");
        return pingService.pingAllServices(customerId, loanId, loanOfferId);
    }

    @GetMapping("/pingForMemoryAndSleep")
    private String pingForMemoryAndSleep () {
        LogUtil.logDebug("LoadController pingForMemoryAndSleep ... ");
        LoadUtil.processLoad();
        return "true";
    }
}