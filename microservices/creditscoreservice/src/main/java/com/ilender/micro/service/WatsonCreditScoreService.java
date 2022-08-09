package com.ilender.micro.service;

import com.ilender.micro.common.ConversionUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.common.MyUtil;
import com.ilender.micro.model.CreditScoreRequestInfo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class WatsonCreditScoreService {

    public double findCreditScore(CreditScoreRequestInfo info) {
        LogUtil.logDebug("WatsonCreditScoreService : findCreditScore : started : " + info);

        String jsonString = ConversionUtil.objectToJsonString(info);
        LogUtil.logDebug("WatsonCreditScoreService : findCreditScore : input Json String : " + jsonString);

        double result = MyUtil.getRandom(40,100);
        LogUtil.logDebug("WatsonCreditScoreService : findCreditScore : result : " + result);
        return result;
    }

}
