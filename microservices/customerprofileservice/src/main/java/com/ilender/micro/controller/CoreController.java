package com.ilender.micro.controller;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.service.LogService;
import com.ilender.micro.service.OpenBankingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@CrossOrigin
@RestController
@RequestMapping("/api/core")
public class CoreController {

    private static final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGDNA_LOGGER);

    @Autowired
    LogService logService;

    @Autowired
    OpenBankingService openBankingService;

    @CrossOrigin
    @GetMapping("/bankTransHistory/{lndCustomerId}")
    @ResponseBody
    private Object bankTransHistory (@PathVariable("lndCustomerId") Integer lndCustomerId) {

        logService.logInfo(logger, "Trans History requested for Customer : " + lndCustomerId);

        return openBankingService.requestBankTransHistory(lndCustomerId);
    }

    @CrossOrigin
    @GetMapping("/loanHistory/{lndCustomerId}")
    @ResponseBody
    private Object loanHistory (@PathVariable("lndCustomerId") Integer lndCustomerId) {

        logService.logInfo(logger, "Loan History requested for Customer : " + lndCustomerId);

        return openBankingService.requestLoanHistory(lndCustomerId);
    }
}
