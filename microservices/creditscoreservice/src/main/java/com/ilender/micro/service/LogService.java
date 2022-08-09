package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    public static final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGDNA_LOGGER);

    @Value("${prop.log.type}")
    private String logType;

    @Value("${prop.log.skip.external.log}")
    private String skipExternalLog;

    @Autowired
    HumioService humioService;

    public void logError(Logger logger, String msg) {
        if (StringUtil.isEqualsIgnoreCase(skipExternalLog, "true")) {
            LogUtil.logError(msg);
        } else {
            if (CommonConstants.LOG_TYPE_LOG_DNA.equalsIgnoreCase(logType)) {
                LogUtil.logError(msg);
                logger.error(msg);
            } else if (CommonConstants.LOG_TYPE_HUMIO.equalsIgnoreCase(logType)) {
                LogUtil.logError(msg);
                humioService.logError(msg);
            } else {
                LogUtil.logError(msg);
            }
        }
    }
    public void logInfo(Logger logger, String msg) {

        if (StringUtil.isEqualsIgnoreCase(skipExternalLog, "true")) {
            LogUtil.logInfo(msg);
        } else {
            if (CommonConstants.LOG_TYPE_LOG_DNA.equalsIgnoreCase(logType)) {
                logger.info(msg);
                LogUtil.logInfo(msg);
            } else if (CommonConstants.LOG_TYPE_HUMIO.equalsIgnoreCase(logType)) {
                humioService.logInfo(msg);
                LogUtil.logInfo(msg);
            } else {
                LogUtil.logInfo(msg);
            }
        }
    }


}
