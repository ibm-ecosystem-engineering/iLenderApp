package com.ilender.micro.service;

import com.ilender.micro.common.LoadUtil;
import com.ilender.micro.common.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class LoadService {

    public static final Logger logger = LoggerFactory.getLogger(LoadService.class);

    public void startMemory(int sizeInKB) {
        LogUtil.logDebug("LoadService : startMemory");
        LoadUtil.startMemory(sizeInKB);
    }
    public void stopMemory() {
        LogUtil.logDebug("LoadService : stopMemory");
        LoadUtil.stopMemory();
    }

    public void consumeMemory(int sizeInKB) {
        LogUtil.logDebug("LoadService : consumeMemory");
        LoadUtil.consumeMemory(sizeInKB);
    }

    public int getMemorySize() {
        LogUtil.logDebug("LoadService : getMemorySize ");
        return LoadUtil.getMemorySize();
    }
    public int clearMemory() {
        LogUtil.logDebug("LoadService : clearMemory ");
        return LoadUtil.clearMemory();
    }

    public static void startSleep(long startMilliSeconds, long incrementMilliSeconds, long requestCount) {
        LogUtil.logDebug("LoadService : startSleep : " + startMilliSeconds + " : " + incrementMilliSeconds + " : " + requestCount);
        LoadUtil.startSleep(startMilliSeconds, incrementMilliSeconds, requestCount);
    }

    public static void stopSleep() {
        LogUtil.logDebug("LoadService : stopSleep ");
        LoadUtil.stopSleep();
    }

    public static void startConsumeMemoryAndThenSleep(int increaseSizeInKB, int maxIncreaseSizeInKBIn, long initialSleepMilliSeconds, long incrementSleepMilliSeconds, long incrementSleepForRequest) {
        LogUtil.logDebug("LoadService : startConsumeMemoryAndThenSleep : " + increaseSizeInKB + " : " + maxIncreaseSizeInKBIn + " : " + initialSleepMilliSeconds+ " : " + incrementSleepMilliSeconds+ " : " + incrementSleepForRequest);
        LoadUtil.startConsumeMemoryAndThenSleep(increaseSizeInKB, maxIncreaseSizeInKBIn, initialSleepMilliSeconds, incrementSleepMilliSeconds, incrementSleepForRequest);
    }

    public String statusConsumeMemoryAndThenSleep() {
        LogUtil.logDebug("LoadService : statusConsumeMemoryAndThenSleep ");
        return LoadUtil.statusConsumeMemoryAndThenSleep();
    }

    public int clearConsumeMemoryAndThenSleep() {
        LogUtil.logDebug("LoadService : clearConsumeMemoryAndThenSleep ");
        return LoadUtil.clearConsumeMemoryAndThenSleep();
    }

    public long getTotalRequestCount() {
        LogUtil.logDebug("LoadService : getTotalRequestCount ");
        return LoadUtil.getTotalRequestCount();
    }

    public long getSleepMilliSeconds() {
        LogUtil.logDebug("LoadService : getSleepMilliSeconds ");
        return LoadUtil.getSleepMilliSeconds();
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