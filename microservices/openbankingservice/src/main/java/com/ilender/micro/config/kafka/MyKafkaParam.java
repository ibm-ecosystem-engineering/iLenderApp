package com.ilender.micro.config.kafka;

import com.ilender.micro.common.LogUtil;

public class MyKafkaParam {

    public static String getServerURL() {
        String result = System.getenv("iLenderEventstreamServerUrl");
        LogUtil.logDebug("MyKafkaParam.getServerURL : iLenderEventstreamServerUrl : " + result);
        return result;
    }

    public static String getTrustStoreLocation() {
        String result = System.getenv("iLenderEventstreamTruststoreLocation");
        LogUtil.logDebug("MyKafkaParam.getServerURL : iLenderEventstreamTruststoreLocation : " + result);
        return result;
    }

    public static String getTrustStorePassword() {
        String result = System.getenv("iLenderEventstreamTruststorePassword");
        LogUtil.logDebug("MyKafkaParam.getServerURL : iLenderEventstreamTruststorePassword : " + result);
        return result;
    }

    public static String getSCRAMUserName() {
        String result = System.getenv("iLenderEventstreamSCRAMUserName");
        LogUtil.logDebug("MyKafkaParam.getServerURL : iLenderEventstreamSCRAMUserName : " + result);
        return result;
    }

    public static String getSCRAMPassword() {
        String result = System.getenv("iLenderEventstreamSCRAMPassword");
        LogUtil.logDebug("MyKafkaParam.getServerURL : iLenderEventstreamSCRAMPassword : " + result);
        return result;
    }

    public static String getGroupId() {
        String result = System.getenv("iLenderEventstreamGroupId");
        LogUtil.logDebug("MyKafkaParam.getGroupId : iLenderEventstreamGroupId : " + result);
        return result;
    }


}

