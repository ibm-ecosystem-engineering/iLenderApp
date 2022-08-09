package com.ilender.micro.controller;

import com.ilender.micro.common.LogUtil;
import com.ilender.micro.model.ErrorTextInfo;
import com.ilender.micro.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoadController {

    @Autowired
    LoadService service;

    @GetMapping("/startMemory/{sizeInKB}")
    @ResponseBody
    private Boolean startMemory (@PathVariable("sizeInKB") int sizeInKB) {
        service.startMemory(sizeInKB);
        return true;
    }

    @GetMapping("/startMemory/{sizeInKB}/{count}")
    @ResponseBody
    private Boolean startMemory (@PathVariable("sizeInKB") int sizeInKB, @PathVariable("count") int count) {
        service.startMemory(sizeInKB, count);
        return true;
    }

    @GetMapping("/leakMemoryUptoMaxPercentage/{sizeInKB}/{maxPercentage}")
    @ResponseBody
    private Boolean leakMemoryUptoMaxPercentage (@PathVariable("sizeInKB") int sizeInKB, @PathVariable("maxPercentage") int maxPercentage) {
        service.leakMemoryUptoMaxPercentage(sizeInKB, maxPercentage);
        return true;
    }

    @GetMapping("/clearLeakMemoryUptoMaxPercentage")
    private Boolean clearLeakMemoryUptoMaxPercentage () {
        service.clearLeakMemoryUptoMaxPercentage();
        return true;
    }

    @GetMapping("/memoryStatus")
    private String memoryStatus () {
        return service.memoryStatus();
    }


    @GetMapping("/stopMemory")
    private Boolean stopMemory () {
        service.stopMemory();
        return true;
    }

    @GetMapping("/memorySize")
    @ResponseBody
    private Integer memorySize () {
        return service.getMemorySize();
    }

    @GetMapping("clearMemory")
    @ResponseBody
    private Integer clearMemory () {
        return service.clearMemory();
    }

    @GetMapping("/consumeMemory/{sizeInKB}")
    @ResponseBody
    private Boolean consumeMemory (@PathVariable("sizeInKB") int sizeInKB) {
        service.consumeMemory(sizeInKB);
        return true;
    }

    @GetMapping("startSleep/{startMilliSeconds}/{incrementMilliSeconds}/{incrementRequestCount}")
    @ResponseBody
    private Boolean startSleep (@PathVariable("startMilliSeconds") long startMilliSeconds, @PathVariable("incrementMilliSeconds") long incrementMilliSeconds, @PathVariable("incrementRequestCount") long requestCount) {
        service.startSleep(startMilliSeconds, incrementMilliSeconds, requestCount);
        return true;
    }

    @GetMapping("stopSleep")
    @ResponseBody
    private Boolean stopSleep () {
        service.stopSleep();
        return true;
    }

    @GetMapping("startConsumeMemoryAndThenSleep")
    @ResponseBody
    private Boolean startConsumeMemoryAndThenSleep2 () {
        service.loadOnStartup();
        return true;
    }

    @GetMapping("startConsumeMemoryAndThenSleep/{increaseSizeInKB}/{maxIncreaseSizeInKBIn}/{initialSleepMilliSeconds}/{incrementSleepMilliSeconds}/{incrementSleepForRequest}")
    @ResponseBody
    private Boolean startConsumeMemoryAndThenSleep (@PathVariable("increaseSizeInKB") int increaseSizeInKB, @PathVariable("maxIncreaseSizeInKBIn") int maxIncreaseSizeInKBIn, @PathVariable("initialSleepMilliSeconds") long initialSleepMilliSeconds, @PathVariable("incrementSleepMilliSeconds") long incrementSleepMilliSeconds, @PathVariable("incrementSleepForRequest") long incrementSleepForRequest) {
        service.startConsumeMemoryAndThenSleep(increaseSizeInKB, maxIncreaseSizeInKBIn, initialSleepMilliSeconds, incrementSleepMilliSeconds, incrementSleepForRequest);
        return true;
    }

    @GetMapping("statusConsumeMemoryAndThenSleep")
    @ResponseBody
    private String statusConsumeMemoryAndThenSleep () {
        return service.statusConsumeMemoryAndThenSleep();
    }

    @GetMapping("clearConsumeMemoryAndThenSleep")
    @ResponseBody
    private Integer clearConsumeMemoryAndThenSleep () {
        return service.clearConsumeMemoryAndThenSleep();
    }


    @GetMapping("/totalRequestCount")
    @ResponseBody
    private Long totalRequestCount () {
        return service.getTotalRequestCount();
    }

    @GetMapping("/sleepMilliSeconds")
    @ResponseBody
    private Long sleepMilliSeconds () {
        return service.getSleepMilliSeconds();
    }


    @GetMapping("/startError")
    @ResponseBody
    private String startError () {
        service.startError();
        return "startError success";
    }

    @GetMapping("/stopError")
    @ResponseBody
    private String stopError () {
        service.stopError();
        return "stopError success";
    }

    @CrossOrigin
    @PostMapping("/errorText")
    private Object errorText(@RequestBody ErrorTextInfo entity) {
        service.setErrorText(entity.getErrorText());
        return service.getErrorText();
    }
}

