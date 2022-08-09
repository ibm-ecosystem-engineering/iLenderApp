package com.ilender.micro.common;

public class StringUtil {


    public static boolean isNullOrEmpty(String text1) {
        boolean result = (text1 == null || text1.isEmpty() );
        return result;
    }

    public static String getLastChar(String text, int charCount) {
        String result = "";
        if (text != null && text.length() >= charCount) {
            result = text.substring(text.length() - charCount);
        }
        return result;
    }



    public static boolean isEqualsIgnoreCase(String text1, String text2) {
        boolean result = false;
        if (text1 == null && text2 == null) {
            result = true;
        } else if (text1 == null || text2 == null ){
            result = false;
        } else if (text1.equalsIgnoreCase(text2)){
            result = true;
        }
        return result;
    }

}
