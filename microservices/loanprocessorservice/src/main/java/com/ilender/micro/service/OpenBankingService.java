package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.kafka.KafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@Service
public class OpenBankingService {

    private static final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGDNA_LOGGER);

    @Autowired
    LogService logService;

    @Value("${urlOpenbankingservice}")
    private String urlOpenBanking;

    @Autowired
    RestUtilService restUtilService;

    public Object requestLoanOffer(KafkaMessage kafkaMessage) {
        String url = urlOpenBanking + "/api/core/requestLoanOffer";
        Object result = restUtilService.callPost(url, kafkaMessage);
        if (result == null) {
            logService.logError(logger, "Unable to get LoanOffer : " + kafkaMessage.getLoanOfferId());
        } else {
            logService.logInfo(logger, "LoanOffer Retrieved  : " + + kafkaMessage.getLoanOfferId());
        }
        return result;
    }

    public Object  requestLoanDetail(KafkaMessage kafkaMessage) {
        String url = urlOpenBanking + "/api/core/requestLoanDetail";
        Object result = restUtilService.callPost(url, kafkaMessage);
        if (result == null) {
            logService.logError(logger, "Unable to get LoanDetail : " + kafkaMessage.getLoanId());
        } else {
            logService.logInfo(logger, "LoanDetail Retrieved  : " + + kafkaMessage.getLoanId());
        }
        return result;
    }

}