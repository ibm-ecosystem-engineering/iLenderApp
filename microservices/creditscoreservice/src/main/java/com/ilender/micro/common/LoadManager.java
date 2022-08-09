package com.ilender.micro.common;

import java.util.ArrayList;
import java.util.List;

public class LoadManager {

    public List<byte[]> listMemory = new ArrayList<>();

    public int memoryIncreaseKB = 0;
    public int maxIncreaseSizeInKB = 0;

    public int maxRequestCount = 0;
    public int leakMemoryMaxPercentage = 0;
    public boolean startErrorFlag = false;

    public long sleepMilliSeconds;

    public long incrementMilliSeconds;
    public long incrementRequestCount;
    public long totalRequestCount;
    private boolean sleepFlag = false;
    private boolean flagMemoryAndSleep = false;
    private boolean flagTypeLeakMemoryUptoMaxPercentageType = false;

    private boolean flagMemoryConsumed = false;

    private String errorText = null;

    public void startMemory(int sizeInKB, int maxRequestCountIn) {
        memoryIncreaseKB = sizeInKB;
        maxRequestCount = maxRequestCountIn;
        totalRequestCount = 0;
    }

    public void leakMemoryUptoMaxPercentage(int sizeInKB, int maxPercentage) {
        flagTypeLeakMemoryUptoMaxPercentageType = true;
        memoryIncreaseKB = sizeInKB;
        leakMemoryMaxPercentage = maxPercentage;
    }

    public int clearLeakMemoryUptoMaxPercentage() {
        leakMemoryMaxPercentage = 0;
        flagTypeLeakMemoryUptoMaxPercentageType = false;
        int result = clearMemory();
        return result;
    }

    public void startMemory(int sizeInKB) {
        memoryIncreaseKB = sizeInKB;
        totalRequestCount = 0;
    }
    public void stopMemory() {
        memoryIncreaseKB = 0;
        maxRequestCount = 0;
        totalRequestCount = 0;
        leakMemoryMaxPercentage = 0;
    }

    public void startConsumeMemoryAndThenSleep(int increaseSizeInKB, int maxIncreaseSizeInKBIn, long initialSleepMilliSeconds, long incrementSleepMilliSeconds, long incrementSleepForRequest) {
        flagMemoryAndSleep = true;
        maxIncreaseSizeInKB = maxIncreaseSizeInKBIn;
        startMemory(increaseSizeInKB);
        startSleep(initialSleepMilliSeconds, incrementSleepMilliSeconds, incrementSleepForRequest);
    }

    public String memoryStatus() {
        StringBuffer text = new StringBuffer();

        text.append("{");
        text.append(",\"increaseSizeInKB\":\"" + memoryIncreaseKB + "\"");
        text.append(",\"increaseSizeInMB\":\"" + memoryIncreaseKB / 1024 + "\"");
        text.append(",\"maxPercentage\":\"" + leakMemoryMaxPercentage + "\"");
        text.append(",\"flagTypeLeakMemoryUptoMaxPercentageType\":\"" + flagTypeLeakMemoryUptoMaxPercentageType + "\"");
        text.append(",\"isMaxPercentageReached\":\"" + isMaxPercentageReached() + "\"");
        text.append(",\"currMemorySize-Array\":\"" + getMemorySize() + "\"");
        text.append(",\"currMemorySizeInMB-Array\":\"" + getMemorySize()/1024 + "\"");
        text.append(",\"totalMemory (request)\":\"" + RunTimeUtil.getTotalMemoryInMiB() + "\"");
        text.append(",\"maxMemory (limit) \":\"" + RunTimeUtil.getMaxMemoryInMiB() + "\"");
        text.append(",\"freeMemory\":\"" + RunTimeUtil.getFreeMemoryInMiB() + "\"");
        text.append(",\"UsedMemoryInMiB\":\"" + RunTimeUtil.getUsedMemoryInMiB() + "\"");
        text.append(",\"PercentageUsed\":\"" + RunTimeUtil.getPercentageUsed() + "\"");

        text.append("}");

        return text.toString();
    }


    public String statusConsumeMemoryAndThenSleep() {
        StringBuffer text = new StringBuffer();

        text.append("{");
        text.append("\"flagMemoryAndSleep\":\"" + flagMemoryAndSleep + "\"");
        text.append(",\"increaseSizeInKB\":\"" + memoryIncreaseKB + "\"");
        text.append(",\"maxIncreaseSizeInKB\":\"" + maxIncreaseSizeInKB + "\"");
        text.append(",\"increaseSizeInMB\":\"" + memoryIncreaseKB / 1024 + "\"");
        text.append(",\"maxIncreaseSizeInMB\":\"" + maxIncreaseSizeInKB / 1024 + "\"");
        text.append(",\"maxPercentage\":\"" + leakMemoryMaxPercentage + "\"");
        text.append(",\"flagMaxPercentageType\":\"" + flagTypeLeakMemoryUptoMaxPercentageType + "\"");
        text.append(",\"isMaxPercentageReached\":\"" + isMaxPercentageReached() + "\"");

        text.append(",\"initialSleepMilliSeconds\":\"" + sleepMilliSeconds + "\"");
        text.append(",\"incrementSleepMilliSeconds\":\"" + incrementMilliSeconds + "\"");
        text.append(",\"incrementSleepForRequest\":\"" + incrementRequestCount + "\"");

        text.append(",\"flagMemoryConsumed\":\"" + flagMemoryConsumed + "\"");
        text.append(",\"currTotalRequestCount\":\"" + totalRequestCount + "\"");
        text.append(",\"maxRequestCount\":\"" + maxRequestCount +  "\"");
        text.append(",\"currMemorySize\":\"" + getMemorySize() + "\"");
        text.append(",\"currMemorySizeInMB\":\"" + getMemorySize()/1024 + "\"");
        text.append(",\"currSleepMilliSeconds\":\"" + sleepMilliSeconds + "\"");
        text.append(",\"currSleepSeconds\":\"" + sleepMilliSeconds/1000 + "\"");
        text.append(",\"currSleepMinutes\":\"" + sleepMilliSeconds/60000 + "\"");
        text.append(",\"errorText\":\"" + getErrorText() + "\"");

        text.append("}");

        return text.toString();
    }
    public int clearConsumeMemoryAndThenSleep() {
        flagMemoryAndSleep = false;
        flagMemoryConsumed = false;
        maxIncreaseSizeInKB = 0;
        int result = clearMemory();
        stopSleep();
        return result;
    }

    public void startSleep(long startMilliSecondsIn, long incrementMilliSecondsIn, long requestCountIn) {
        sleepMilliSeconds = startMilliSecondsIn;
        incrementMilliSeconds = incrementMilliSecondsIn;
        incrementRequestCount = requestCountIn;
        totalRequestCount = 0;
        sleepFlag = true;
    }

    public void stopSleep() {
        sleepMilliSeconds = 0;
        incrementMilliSeconds = 0;
        incrementRequestCount = 0;
        totalRequestCount = 0;
        sleepFlag = false;
    }

    public int clearMemory() {
        int result = listMemory.size();
        listMemory.clear();
        System.gc();
        return result;
    }

    public void consumeMemory(int sizeInKB) {
        processMemory(sizeInKB);
    }

    public int getMemorySize() {
        return listMemory.size();
    }

    public long getTotalRequestCount() {
        return totalRequestCount;
    }

    public long getSleepMilliSeconds() {
        return sleepMilliSeconds;
    }

    public  Object processLoad() {

        Object result = null;
        totalRequestCount++;
        LogUtil.logDebug("LoadUtil processLoad totalRequestCount : " + totalRequestCount);
        LogUtil.logDebug("LoadUtil processLoad flagMaxPercentageType : " + flagTypeLeakMemoryUptoMaxPercentageType);

        if (flagMemoryAndSleep) {
            LogUtil.logDebug("LoadUtil processLoad flagMemoryAndSleep : " + flagMemoryAndSleep);

            if (flagMemoryConsumed) {
                LogUtil.logDebug("LoadUtil processLoad processSleep : ");

                processSleep();
            } else {
                LogUtil.logDebug("LoadUtil processLoad processMemory : " );

                result = processMemory(memoryIncreaseKB);

                LogUtil.logDebug("LoadUtil processLoad getMemorySize() : " + getMemorySize());
                LogUtil.logDebug("LoadUtil processLoad maxIncreaseSizeInKB : " + maxIncreaseSizeInKB);

                if (getMemorySize() >= maxIncreaseSizeInKB) {
                    flagMemoryConsumed = true;
                }
                LogUtil.logDebug("LoadUtil processLoad flagMemoryConsumed : " + flagMemoryConsumed);
            }

        } else if (flagTypeLeakMemoryUptoMaxPercentageType) {
            if (!isMaxPercentageReached()) {
                result = processMemory(memoryIncreaseKB);
            }
        } else if (!isMaxRequestCountReached()) {
            LogUtil.logDebug("LoadUtil processLoad !isMaxRequestCountReached : ");
            result = processMemory(memoryIncreaseKB);
            processSleep();
        }
        return result;
    }

    public boolean canAllocateFullMemory(int memoryInKB) {
        boolean result = RunTimeUtil.getFreeMemoryInKB() > memoryInKB;
        return result;
    }

    public boolean isMaxPercentageReached() {
        boolean result = false;
        if (leakMemoryMaxPercentage > 0 && RunTimeUtil.getPercentageUsed() >= leakMemoryMaxPercentage) {
            result = true;
        }
        LogUtil.logDebug("LoadUtil isMaxPercentageReached RunTimeUtil.getPercentageUsed() : " + RunTimeUtil.getPercentageUsed());
        LogUtil.logDebug("LoadUtil isMaxPercentageReached Result :  " + result);
        return result;
    }

    public boolean isMaxRequestCountReached() {
        boolean result = false;
        if (maxRequestCount > 0 && totalRequestCount >= maxRequestCount) {
            result = true;
        }
        LogUtil.logDebug("LoadUtil isMaxRequestCountReached totalRequestCount : " + totalRequestCount);

        LogUtil.logDebug("LoadUtil isMaxRequestCountReached Result :  " + result);
        return result;
    }

    private void processSleep() {
//        LogUtil.logDebug("LoadUtil processSleep started : " + sleepMilliSeconds);

        if (sleepFlag) {
            if (totalRequestCount % incrementRequestCount == 0) {
                sleepMilliSeconds += incrementMilliSeconds;
            }

            if (sleepMilliSeconds > 0) {
                try {
                    Thread.sleep(sleepMilliSeconds);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

//        LogUtil.logDebug("LoadUtil processSleep completed: " + sleepMilliSeconds);
    }

    private Object processMemory(int memoryIncreaseKBIn) {
        LogUtil.logDebug("LoadUtil processMemory started: " + memoryIncreaseKBIn);
        Object result = null;

        synchronized (this) {
            try {
                if (canAllocateFullMemory(memoryIncreaseKBIn)) {
                    listMemory.add(new byte[memoryIncreaseKBIn]);
                } else {
                    for (int i = 0; i < memoryIncreaseKBIn; i++) {
                        if (isMaxPercentageReached()) {
                            break;
                        } else {
                            listMemory.add(new byte[1024]);
                        }
                    }
                }
            } catch ( Exception e) {
                e.printStackTrace();
                result = e.getLocalizedMessage()       ;
                LogUtil.logDebug("LoadUtil processMemory completed result: " + result);
            }
        }


        try {
            for (int i = 0; i < memoryIncreaseKBIn; i++) {

                if (isMaxPercentageReached()) {
                    break;
                } else {
                    synchronized (this) {
                        listMemory.add(new byte[1024]);
                    }
                }
            }
        } catch ( Exception e) {
            e.printStackTrace();
            result = e.getLocalizedMessage()       ;
            LogUtil.logDebug("LoadUtil processMemory completed result: " + result);
        }

        LogUtil.logDebug("LoadUtil processMemory completed : " + listMemory.size() + " KB");
        return result;
    }

    public void startError () {
        startErrorFlag = true;
    }

    public void stopError () {
        startErrorFlag = false;
    }

    public boolean isStartErrorFlag () {
        return startErrorFlag ;
    }

    public boolean isFlagMemoryConsumed () {
        return flagMemoryConsumed ;
    }

    public void setErrorText(String errorTextIn) {
        errorText = errorTextIn;
    }

    public String getErrorText() {
        String result = errorText;
        if (result == null || result.isEmpty()) {
            result = "CreditScore Processing failed due to the out of memory error. : java.lang.OutOfMemoryError";
        }
        return result;
    }
}

