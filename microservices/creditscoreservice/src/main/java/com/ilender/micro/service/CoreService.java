package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.LoadManager;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.model.CreditScoreRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Configuration
@Service
public class CoreService {

    private static final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGDNA_LOGGER);

    @Value("${prop.humio.url}")
    private String humioUrl;
    
    @Autowired
    LogService logService;

    @Autowired
    LoadService loadService;

    int errorScore = 0;

    @Autowired
    LoanService loanService;

    @Autowired
    CustomerProfileService customerProfileService;

    @Autowired
    WatsonCreditScoreService watsonCreditScoreService;

    public double creditScore (Integer lndCustomerId, Integer lngLoanId) {
        LogUtil.logDebug("CoreController : creditScore : lndCustomerId : " + lndCustomerId);
        LogUtil.logDebug("CoreController : creditScore : lngLoanId : " + lngLoanId);

        double result = 0;

        if (loadService.isStartErrorFlag()) {
            logService.logError(logger, loadService.getErrorText());
        } else {

            if (loadService.isFlagMemoryConsumed()) {
                logService.logError(logger, loadService.getErrorText());
            }

            if (loadService.isMaxRequestCountReached()) {
                logService.logError(logger, loadService.getErrorText());
            }

            if (loadService.isMaxPercentageReached()) {
                logService.logError(logger, loadService.getErrorText());
                // for (int i=0; i<= 200; i++) {
                //     logService.logError(logger, loadService.getErrorText());
                // }
            }

            List list1 = customerProfileService.fetchLoanHistory(lndCustomerId);
            List list2 = customerProfileService.fetchTransHistory(lndCustomerId);
            CreditScoreRequestInfo info = new CreditScoreRequestInfo(lndCustomerId, list1, list2);
            result = watsonCreditScoreService.findCreditScore(info);

            //For error simulation....
            if (errorScore > 0) {
                result = errorScore;
            }

            loanService.updateCreditScore(lngLoanId, result);

            //Process load
            Object processLoadResult = loadService.processLoad();
            if (processLoadResult != null) {
                logService.logError(logger, "Error occurred during  creditScore processing. " + processLoadResult);
            }

            logService.logInfo(logger, "CoreController creditScore : " + result);

            LogUtil.logDebug("CoreController : creditScore : result : " + result);
        }

        return result;
    }

    public Integer startCreditScoreError (Integer score) {
        LogUtil.logDebug("CoreController : startCreditScoreError : score : " + score);

        errorScore = score;

        LogUtil.logDebug("CoreController : startCreditScoreError : result : " + errorScore);
        return errorScore;
    }

    public Integer stopCreditScoreError () {
        LogUtil.logDebug("CoreController : stopCreditScoreError :  " + errorScore);

        errorScore = 0;

        LogUtil.logDebug("CoreController : stopCreditScoreError " );
        return errorScore;
    }

    public Integer creditScoreError () {
        LogUtil.logDebug("CoreController : creditScoreError :  " + errorScore);
        return errorScore;
    }


}