package com.ilender.micro.common;

import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RunTimeUtil {

    private static final long MEGABYTE_FACTOR = 1024L * 1024L;
    private static final DecimalFormat ROUNDED_DOUBLE_DECIMALFORMAT;
    private static final String MIB = "MiB";
    public static List<byte[]> listMemory = new ArrayList<>();

    static {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(',');
        ROUNDED_DOUBLE_DECIMALFORMAT = new DecimalFormat("####0.00", otherSymbols);
        ROUNDED_DOUBLE_DECIMALFORMAT.setGroupingUsed(false);
    }
    public static void main(String[] args) {
        printAllocateMemory();
    }

    public static void printAllocateMemory() {
        allocateMemory(1);
        allocateMemory(50);
        allocateMemory(200);
        allocateMemory(100);
        allocateMemory(2000);
        allocateMemory(3000);
        allocateMemory(800);
        allocateMemory(51);
        allocateMemory(102);
        allocateMemory(103);
        allocateMemory(54);
        allocateMemory(105);
        allocateMemory(106);
        allocateMemory(107);
    }


    public static void allocateMemory(int sizeInMB) {
        processMemory(1024*sizeInMB);

     }

    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    public static long getUsedMemory() {
        return getTotalMemory() - getFreeMemory();
    }

    public static long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }
    public static long getFreeMemoryInKB() {
        return NumberUtil.divide(Runtime.getRuntime().freeMemory(), 1024);
    }

    public static String getTotalMemoryInMiB() {
        double totalMiB = bytesToMiB(getTotalMemory());
        return String.format("%s %s", ROUNDED_DOUBLE_DECIMALFORMAT.format(totalMiB), MIB);
    }

    public static int getFreeMemoryInMB() {
        int result =  (int) bytesToMiB(getFreeMemory());
        return result;
    }
    public static String getFreeMemoryInMiB() {
        double freeMiB = bytesToMiB(getFreeMemory());
        return String.format("%s %s", ROUNDED_DOUBLE_DECIMALFORMAT.format(freeMiB), MIB);
    }

    public static String getUsedMemoryInMiB() {
        double usedMiB = bytesToMiB(getUsedMemory());
        return String.format("%s %s", ROUNDED_DOUBLE_DECIMALFORMAT.format(usedMiB), MIB);
    }

    public static String getMaxMemoryInMiB() {
        double maxMiB = bytesToMiB(getMaxMemory());
        return String.format("%s %s", ROUNDED_DOUBLE_DECIMALFORMAT.format(maxMiB), MIB);
    }

    public static double getPercentageUsed() {
        return ((double) getUsedMemory() / getTotalMemory()) * 100;
    }

    public static String getPercentageUsedFormatted() {
        double usedPercentage = getPercentageUsed();
        return ROUNDED_DOUBLE_DECIMALFORMAT.format(usedPercentage) + "%";
    }

    private static double bytesToMiB(long bytes) {
        return ((double) bytes / MEGABYTE_FACTOR);
    }

    public static String getHostAdress() {
        try {
            java.net.InetAddress addr = java.net.InetAddress.getLocalHost();
            return addr.getHostAddress();
        } catch (UnknownHostException e) {
            // looks like a strange machine
           LogUtil.logDebug(e.getMessage());
        }
        return "";
    }

    public static String getHostName() {
        try {
            java.net.InetAddress addr = java.net.InetAddress.getLocalHost();
            return addr.getHostName();
        } catch (UnknownHostException e) {
            // looks like a strange machine
            LogUtil.logDebug(e.getMessage());
        }
        return "";
    }

    public static String getSystemInformation() {
        return String.format("SystemInfo=Current heap:%s; Used:%s; Free:%s; Maximum Heap:%s; Percentage Used:%s",
                getTotalMemoryInMiB(),
                getUsedMemoryInMiB(),
                getFreeMemoryInMiB(),
                getMaxMemoryInMiB(),
                getPercentageUsedFormatted());
    }

    public static Object processMemory(int memoryIncreaseKBIn) {
        LogUtil.logDebug("LoadUtil processMemory started: " + memoryIncreaseKBIn);

        Object result = null;

        if (getPercentageUsed() >= 90) {
            LogUtil.logDebug("LoadUtil processMemory memory is full ............ " );
        } else {

            try {
                for (int i = 0; i < memoryIncreaseKBIn; i++) {
                    byte mybyte[] = new byte[1024];
                    listMemory.add(mybyte);
                }
            } catch ( Exception e) {
                e.printStackTrace();
                result = e.getLocalizedMessage()       ;
                LogUtil.logDebug("LoadUtil processMemory completed result: " + result);
            }
            LogUtil.logDebug("LoadUtil processMemory completed : " + listMemory.size() + " KB");
        }

        return result;
    }
}
