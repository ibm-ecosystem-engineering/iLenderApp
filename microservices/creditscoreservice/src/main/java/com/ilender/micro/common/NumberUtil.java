package com.ilender.micro.common;

public class NumberUtil {

    public static String add(String number1, int number2) {
        int value = stringToInt(number1) + number2;
        String result = String.valueOf(value);
        return result;
    }

    public static int stringToInt(String number1) {
        int result = 0;
        try{
            result = Integer.parseInt(number1);
        } catch (Exception e) {
        }
        return result;
    }

    public static long divide(long number1, long number2) {
        long result = 0;
        try{
            result = number1/number2;
        } catch (Exception e) {
        }
        return result;
    }



}
