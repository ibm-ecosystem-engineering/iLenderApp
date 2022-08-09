package com.ilender.micro.common;

import java.util.ArrayList;
import java.util.List;

public class LoadUtil {

    public static List<byte[]> listMemory = new ArrayList<>();

    public static int memoryIncreaseKB = 0;
    public static int maxIncreaseSizeInKB = 0;

    public static boolean startErrorFlag = false;

    public static long sleepMilliSeconds;

    public static long incrementMilliSeconds;
    public static long incrementRequestCount;
    public static long totalRequestCount;
    private  static boolean sleepFlag = false;
    private  static boolean flagMemoryAndSleep = false;
    private  static boolean flagMemoryConsumed = false;

    public static void startMemory(int sizeInKB) {
        memoryIncreaseKB = sizeInKB;
    }
    public static void stopMemory() {
        memoryIncreaseKB = 0;
    }

    public static void startConsumeMemoryAndThenSleep(int increaseSizeInKB, int maxIncreaseSizeInKBIn, long initialSleepMilliSeconds, long incrementSleepMilliSeconds, long incrementSleepForRequest) {
        flagMemoryAndSleep = true;
        maxIncreaseSizeInKB = maxIncreaseSizeInKBIn;
        startMemory(increaseSizeInKB);
        startSleep(initialSleepMilliSeconds, incrementSleepMilliSeconds, incrementSleepForRequest);
    }
    public static String statusConsumeMemoryAndThenSleep() {
        StringBuffer text = new StringBuffer();

        text.append("{");
        text.append("\"flagMemoryAndSleep\":\"" + flagMemoryAndSleep + "\"");
        text.append(",\"increaseSizeInKB\":\"" + memoryIncreaseKB + "\"");
        text.append(",\"maxIncreaseSizeInKB\":\"" + maxIncreaseSizeInKB + "\"");
        text.append(",\"initialSleepMilliSeconds\":\"" + sleepMilliSeconds + "\"");
        text.append(",\"incrementSleepMilliSeconds\":\"" + incrementMilliSeconds + "\"");
        text.append(",\"incrementSleepForRequest\":\"" + incrementRequestCount + "\"");

        text.append(",\"flagMemoryConsumed\":\"" + flagMemoryConsumed + "\"");
        text.append(",\"currTotalRequestCount\":\"" + totalRequestCount + "\"");
        text.append(",\"currMemorySize\":\"" + getMemorySize() + "\"");
        text.append(",\"currSleepMilliSeconds\":\"" + sleepMilliSeconds + "\"");

        text.append("}");

        return text.toString();
    }
    public static int clearConsumeMemoryAndThenSleep() {
        flagMemoryAndSleep = false;
        flagMemoryConsumed = false;
        maxIncreaseSizeInKB = 0;
        int result = clearMemory();
        stopSleep();
        return result;
    }

    public static void startSleep(long startMilliSecondsIn, long incrementMilliSecondsIn, long requestCountIn) {
        sleepMilliSeconds = startMilliSecondsIn;
        incrementMilliSeconds = incrementMilliSecondsIn;
        incrementRequestCount = requestCountIn;
        totalRequestCount = 0;
        sleepFlag = true;
    }

    public static void stopSleep() {
        sleepMilliSeconds = 0;
        incrementMilliSeconds = 0;
        incrementRequestCount = 0;
        totalRequestCount = 0;
        sleepFlag = false;
    }

    public static int clearMemory() {
        int result = listMemory.size();
        listMemory.clear();
        System.gc();
        return result;
    }
    public static void consumeMemory(int sizeInKB) {
        processMemory(sizeInKB);
    }

    public static int getMemorySize() {
        return listMemory.size();
    }

    public static long getTotalRequestCount() {
        return totalRequestCount;
    }

    public static long getSleepMilliSeconds() {
        return sleepMilliSeconds;
    }

    public static void processLoad() {
        totalRequestCount++;

        if (flagMemoryAndSleep) {
            if (flagMemoryConsumed) {
                processSleep();
            } else {
                processMemory(memoryIncreaseKB);
                if (getMemorySize() >= maxIncreaseSizeInKB) {
                    flagMemoryConsumed = true;
                }
            }
        } else {
            processMemory(memoryIncreaseKB);
            processSleep();
        }
    }

    private static void processSleep() {
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

    private static int processMemory(int memoryIncreaseKBIn) {
        LogUtil.logDebug("LoadUtil processMemory started: " + memoryIncreaseKBIn);

        for (int i = 0; i < memoryIncreaseKBIn; i++) {
            byte mybyte[] = new byte[1024];
            listMemory.add(mybyte);
        }

        return listMemory.size();
    }

    public static void startError () {
        startErrorFlag = true;
    }

    public static void stopError () {
        startErrorFlag = false;
    }

    public static boolean isStartErrorFlag () {
        return startErrorFlag ;
    }
}

