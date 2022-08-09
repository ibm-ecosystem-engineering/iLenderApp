package com.ilender.micro.controller;

import com.ilender.micro.common.LogUtil;
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
    CoreService coreService;

    @GetMapping("/creditScore/{lndCustomerId}/{lndLoanId}")
    @ResponseBody
    private double creditScore (@PathVariable("lndCustomerId") Integer lndCustomerId, @PathVariable("lndLoanId") Integer lndLoanId) {
        double result = coreService.creditScore(lndCustomerId, lndLoanId);
        LogUtil.logDebug("CoreController : creditScore : result : " + result);
        return result;
    }

    @GetMapping("/startCreditScoreError/{score}")
    @ResponseBody
    private Integer startCreditScoreError (@PathVariable("score") Integer score) {
        Integer result = coreService.startCreditScoreError(score);
        LogUtil.logDebug("CoreController : startCreditScoreError : result : " + result);
        return result;
    }

    @GetMapping("/stopCreditScoreError")
    @ResponseBody
    private Integer stopCreditScoreError () {
        Integer result = coreService.stopCreditScoreError();
        LogUtil.logDebug("CoreController : stopCreditScoreError : result : " + result);
        return result;
    }

    @GetMapping("/creditScoreError/{score}")
    @ResponseBody
    private Integer creditScoreError () {
        Integer result = coreService.creditScoreError();
        LogUtil.logDebug("CoreController : creditScoreError : result : " + result);
        return result;
    }



}
