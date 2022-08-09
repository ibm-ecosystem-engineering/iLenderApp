package com.ilender.micro.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    static String patternMMMYYYY = "MMM yyyy";
    static String patternDDMMMYYYY = "dd-MMM-yyyy";
    static String patternLog = "yyyy-MM-dd HH:mm:ss.SSS";
    static String patternOpenBank = "yyyy-MM-ddTHH:mm:ss.SSS";

    public static String convertMMMYYY(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternMMMYYYY);
        String dateString = simpleDateFormat.format(new Date());
        return dateString;
    }

    public static String convertDDMMMYYY(Date date) {
        String dateString = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternDDMMMYYYY);
            dateString = simpleDateFormat.format(date);
        } catch (Exception e) {
            //Do Nothing.
        }
        return dateString;
    }

    public static String currenTimeForLog() {
        String dateString = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternLog);
            dateString = simpleDateFormat.format(new Date());
        } catch (Exception e) {
            //Do Nothing.
        }
        return dateString;
    }

    public static Date openBankDateStringToDate(String dateString) {
        Date result = null;
        try {
            result = new SimpleDateFormat(patternOpenBank).parse(dateString);
        } catch (Exception e) {
            //Do Nothing.
        }
        return result;
    }
}
