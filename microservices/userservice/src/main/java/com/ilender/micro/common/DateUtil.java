package com.ilender.micro.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    static String patternMMMYYYY = "MMM yyyy";
    static String patternDDMMMYYYY = "dd-MMM-yyyy";
    static String patternLog = "yyyy-MM-dd HH:mm:ss.SSS";

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
            e.printStackTrace();
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

    public static Date addYearToDate(Date givenDate, int yearToAdd) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(givenDate);
        cal.add(Calendar.YEAR, yearToAdd);
        Date result = cal.getTime();
        return result;
    }


}
