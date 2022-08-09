package com.ilender.micro.controller;

import com.ilender.micro.common.LoadUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.kafka.KafkaMessage;
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

    @CrossOrigin
    @GetMapping("/requestBankTransHistory/{lndCustomerId}")
    @ResponseBody
    private Object bankTransHistory (@PathVariable("lndCustomerId") Integer lndCustomerId) {
        return service.requestBankTransHistory(lndCustomerId);
    }

    @CrossOrigin
    @GetMapping("/requestLoanHistory/{lndCustomerId}")
    @ResponseBody
    private Object loanHistory (@PathVariable("lndCustomerId") Integer lndCustomerId) {
        return service.requestLoanHistory(lndCustomerId);
    }

    //Note: This method would be called from LoanProcessorService only when Kafka is disabled.
    @CrossOrigin
    @PostMapping("/requestLoanOffer")
    @ResponseBody
    private Object requestLoanOffer (@RequestBody KafkaMessage entity) {
        return service.requestLoanOffer(entity);
    }

    //Note: This method would be called from LoanProcessorService only when Kafka is disabled.
    @CrossOrigin
    @PostMapping("/requestLoanDetail")
    @ResponseBody
    private Object requestLoanDetail (@RequestBody KafkaMessage entity) {
        return service.requestLoanDetail(entity);
    }

    public void startError () {
        LogUtil.logDebug("CoreController : startError ");
        LoadUtil.startError();
    }

    public void stopError () {
        LogUtil.logDebug("CoreController : stopError ");
        LoadUtil.stopError();
    }
}
