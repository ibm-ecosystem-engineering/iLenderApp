package com.ilender.micro.service;

import com.ilender.micro.common.LoadManager;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class LoadService {

    public static final Logger logger = LoggerFactory.getLogger(LoadService.class);

    @Value("${prop.custom.error.flag}")
    private String customeErrorFlag;

    @Value("${prop.custom.error.msg}")
    private String customeErrorMsg;

    @Value("${prop.load.simulation}")
    private String loadSimulation;

    @Value("${prop.load.increaseSizeInKB}")
    private int increaseSizeInKB;

    @Value("${prop.load.maxIncreaseSizeInKB}")
    private int maxIncreaseSizeInKB;

    @Value("${prop.load.initialSleepMilliSeconds}")
    private int initialSleepMilliSeconds;

    @Value("${prop.load.incrementSleepMilliSeconds}")
    private long incrementSleepMilliSeconds;

    @Value("${prop.load.incrementSleepForRequest}")
    private long incrementSleepForRequest;

    LoadManager loadManager = new LoadManager();
    
    public void loadOnStartup() {
        LogUtil.logDebug("LoadService : loadOnStartup");
        LogUtil.logDebug("LoadService : loadSimulation :" + loadSimulation);

        if (StringUtil.isEqualsIgnoreCase(loadSimulation, "true")) {
            startConsumeMemoryAndThenSleep(increaseSizeInKB, maxIncreaseSizeInKB, initialSleepMilliSeconds, incrementSleepMilliSeconds, incrementSleepForRequest);
        }
    }
    public void startMemory(int sizeInKB, int maxRequestCount) {
        LogUtil.logDebug("LoadService : startMemory");
        loadManager.startMemory(sizeInKB, maxRequestCount);
    }

    public void leakMemoryUptoMaxPercentage(int sizeInKB, int maxPercentage) {
        LogUtil.logDebug("LoadService : leakMemoryUptoMaxPercentage");
        loadManager.leakMemoryUptoMaxPercentage(sizeInKB, maxPercentage);
    }

    public void startMemory(int sizeInKB) {
        LogUtil.logDebug("LoadService : startMemory");
        loadManager.startMemory(sizeInKB);
    }
    public void stopMemory() {
        LogUtil.logDebug("LoadService : stopMemory");
        loadManager.stopMemory();
    }

    public void consumeMemory(int sizeInKB) {
        LogUtil.logDebug("LoadService : consumeMemory");
        loadManager.consumeMemory(sizeInKB);
    }

    public int getMemorySize() {
        LogUtil.logDebug("LoadService : getMemorySize ");
        return loadManager.getMemorySize();
    }
    public int clearMemory() {
        LogUtil.logDebug("LoadService : clearMemory ");
        return loadManager.clearMemory();
    }
    public int clearLeakMemoryUptoMaxPercentage() {
        LogUtil.logDebug("LoadService : clearLeakMemoryUptoMaxPercentage ");
        return loadManager.clearLeakMemoryUptoMaxPercentage();
    }
    public String memoryStatus() {
        LogUtil.logDebug("LoadService : memoryStatus ");
        return loadManager.memoryStatus();
    }

    public void startSleep(long startMilliSeconds, long incrementMilliSeconds, long requestCount) {
        LogUtil.logDebug("LoadService : startSleep : " + startMilliSeconds + " : " + incrementMilliSeconds + " : " + requestCount);
        loadManager.startSleep(startMilliSeconds, incrementMilliSeconds, requestCount);
    }

    public void stopSleep() {
        LogUtil.logDebug("LoadService : stopSleep ");
        loadManager.stopSleep();
    }

    public String startConsumeMemoryAndThenSleep(int increaseSizeInKB, int maxIncreaseSizeInKBIn, long initialSleepMilliSeconds, long incrementSleepMilliSeconds, long incrementSleepForRequest) {
        LogUtil.logDebug("LoadService : startConsumeMemoryAndThenSleep : " + increaseSizeInKB + " : " + maxIncreaseSizeInKBIn + " : " + initialSleepMilliSeconds+ " : " + incrementSleepMilliSeconds+ " : " + incrementSleepForRequest);
        loadManager.startConsumeMemoryAndThenSleep(increaseSizeInKB, maxIncreaseSizeInKBIn, initialSleepMilliSeconds, incrementSleepMilliSeconds, incrementSleepForRequest);
        return loadManager.statusConsumeMemoryAndThenSleep();
    }

    public String statusConsumeMemoryAndThenSleep() {
        LogUtil.logDebug("LoadService : statusConsumeMemoryAndThenSleep ");
        return loadManager.statusConsumeMemoryAndThenSleep();
    }

    public int clearConsumeMemoryAndThenSleep() {
        LogUtil.logDebug("LoadService : clearConsumeMemoryAndThenSleep ");
        return loadManager.clearConsumeMemoryAndThenSleep();
    }

    public long getTotalRequestCount() {
        LogUtil.logDebug("LoadService : getTotalRequestCount ");
        return loadManager.getTotalRequestCount();
    }

    public long getSleepMilliSeconds() {
        LogUtil.logDebug("LoadService : getSleepMilliSeconds ");
        return loadManager.getSleepMilliSeconds();
    }

    public void startError () {
        LogUtil.logDebug("CoreController : startError ");
        loadManager.startError();
    }

    public void stopError () {
        LogUtil.logDebug("CoreController : stopError ");
        loadManager.stopError();
    }

    public boolean isStartErrorFlag () {
        return loadManager.isStartErrorFlag();
    }

    public boolean isFlagMemoryConsumed () {
        return loadManager.isFlagMemoryConsumed() ;
    }

    public Object processLoad () {
        return loadManager.processLoad() ;
    }

    public boolean isMaxRequestCountReached () {
        return loadManager.isMaxRequestCountReached() ;
    }
    public boolean isMaxPercentageReached () {
        return loadManager.isMaxPercentageReached() ;
    }

    public void setErrorText(String errorText) {
        loadManager.setErrorText(errorText);
    }

    public String getErrorText() {
        String result = null;
        if (StringUtil.isEqualsIgnoreCase(customeErrorFlag, "true")) {
            result = customeErrorMsg;
        } else {
            result = loadManager.getErrorText();
        }
        return result;
    }
}