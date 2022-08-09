package com.ilender.micro.common;

public class LogUtil {

    public static boolean debugFlag = false;

    //Static block
    {
        init();
    }

    public static void init() {
        String temp = System.getenv("DEBUG_LOG");
        if (temp != null && temp.equals("TRUE")) {
            debugFlag = true;
        }
    }

    public static void log(String msg) {
        System.out.println(msg);
    }

    public static void logDebug(String msg) {
        if (debugFlag) {
            log("Debug: " + msg);
        }
    }

    public static void logInfo(String msg) {
        log("Info: " + msg);
    }

    public static void logError(String msg) {
        log("Error: " + msg);
    }
}
