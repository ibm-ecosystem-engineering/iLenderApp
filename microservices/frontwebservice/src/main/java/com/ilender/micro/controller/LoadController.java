package com.ilender.micro.controller;

import com.ilender.micro.common.LogUtil;
import com.ilender.micro.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadController {

    @Autowired
    LoadService service;

    @GetMapping("/startMemory/{sizeInKB}")
    @ResponseBody
    private Boolean startMemory (@PathVariable("sizeInKB") int sizeInKB) {
        LogUtil.logDebug("LoadController startMemory ... " + sizeInKB);
        service.startMemory(sizeInKB);
        return true;
    }

    @GetMapping("/stopMemory")
    private Boolean stopMemory () {
        LogUtil.logDebug("LoadController stopMemory ... ");
        service.stopMemory();
        return true;
    }

    @GetMapping("/memorySize")
    @ResponseBody
    private Integer memorySize () {
        LogUtil.logDebug("LoadController memorySize ... " );
        return service.getMemorySize();
    }

    @GetMapping("clearMemory")
    @ResponseBody
    private Integer clearMemory () {
        LogUtil.logDebug("LoadController clearMemory ... " );
        return service.clearMemory();
    }

    @GetMapping("/consumeMemory/{sizeInKB}")
    @ResponseBody
    private Boolean consumeMemory (@PathVariable("sizeInKB") int sizeInKB) {
        LogUtil.logDebug("LoadController consumeMemory ... " + sizeInKB);
        service.consumeMemory(sizeInKB);
        return true;
    }

    @GetMapping("startSleep/{startMilliSeconds}/{incrementMilliSeconds}/{incrementRequestCount}")
    @ResponseBody
    private Boolean startSleep (@PathVariable("startMilliSeconds") long startMilliSeconds, @PathVariable("incrementMilliSeconds") long incrementMilliSeconds, @PathVariable("incrementRequestCount") long requestCount) {
        LogUtil.logDebug("LoadController startSleep startMilliSeconds ... " + startMilliSeconds);
        LogUtil.logDebug("LoadController startSleep incrementMilliSeconds ... " + incrementMilliSeconds);
        LogUtil.logDebug("LoadController startSleep incrementRequestCount ... " + requestCount);
        service.startSleep(startMilliSeconds, incrementMilliSeconds, requestCount);
        return true;
    }

    @GetMapping("stopSleep")
    @ResponseBody
    private Boolean stopSleep () {
        LogUtil.logDebug("LoadController stopSleep ... " );
        service.stopSleep();
        return true;
    }

    @GetMapping("startConsumeMemoryAndThenSleep/{increaseSizeInKB}/{maxIncreaseSizeInKBIn}/{initialSleepMilliSeconds}/{incrementSleepMilliSeconds}/{incrementSleepForRequest}")
    @ResponseBody
    private Boolean startConsumeMemoryAndThenSleep (@PathVariable("increaseSizeInKB") int increaseSizeInKB, @PathVariable("maxIncreaseSizeInKBIn") int maxIncreaseSizeInKBIn, @PathVariable("initialSleepMilliSeconds") long initialSleepMilliSeconds, @PathVariable("incrementSleepMilliSeconds") long incrementSleepMilliSeconds, @PathVariable("incrementSleepForRequest") long incrementSleepForRequest) {
        LogUtil.logDebug("LoadController startConsumeMemoryAndThenSleep increaseSizeInKB ... " + increaseSizeInKB);
        LogUtil.logDebug("LoadController startConsumeMemoryAndThenSleep maxIncreaseSizeInKBIn ... " + maxIncreaseSizeInKBIn);
        LogUtil.logDebug("LoadController startConsumeMemoryAndThenSleep initialSleepMilliSeconds ... " + initialSleepMilliSeconds);
        LogUtil.logDebug("LoadController startConsumeMemoryAndThenSleep incrementSleepMilliSeconds ... " + incrementSleepMilliSeconds);
        LogUtil.logDebug("LoadController startConsumeMemoryAndThenSleep incrementSleepForRequest ... " + incrementSleepForRequest);
        service.startConsumeMemoryAndThenSleep(increaseSizeInKB, maxIncreaseSizeInKBIn, initialSleepMilliSeconds, incrementSleepMilliSeconds, incrementSleepForRequest);
        return true;
    }

    @GetMapping("statusConsumeMemoryAndThenSleep")
    @ResponseBody
    private String statusConsumeMemoryAndThenSleep () {
        LogUtil.logDebug("LoadController statusConsumeMemoryAndThenSleep ... " );
        return service.statusConsumeMemoryAndThenSleep();
    }

    @GetMapping("clearConsumeMemoryAndThenSleep")
    @ResponseBody
    private Integer clearConsumeMemoryAndThenSleep () {
        LogUtil.logDebug("LoadController clearConsumeMemoryAndThenSleep ... " );
        return service.clearConsumeMemoryAndThenSleep();
    }


    @GetMapping("/totalRequestCount")
    @ResponseBody
    private Long totalRequestCount () {
        LogUtil.logDebug("LoadController totalRequestCoun ... " );
        return service.getTotalRequestCount();
    }

    @GetMapping("/sleepMilliSeconds")
    @ResponseBody
    private Long sleepMilliSeconds () {
        LogUtil.logDebug("LoadController sleepMilliSeconds ... " );
        return service.getSleepMilliSeconds();
    }


    @GetMapping("/startError")
    @ResponseBody
    private String startError () {
        LogUtil.logDebug("CoreController : startError ");
        service.startError();
        return "startError success";
    }

    @GetMapping("/stopError")
    @ResponseBody
    private String stopError () {
        LogUtil.logDebug("CoreController : stopError ");
        service.stopError();
        return "stopError success";
    }

}

