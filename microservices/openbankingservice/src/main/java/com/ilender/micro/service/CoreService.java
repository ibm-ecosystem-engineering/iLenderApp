package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.LoadUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.kafka.KafkaMessage;
import com.ilender.micro.model.LoanDetailInfo;
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

    @Autowired
    LogService logService;

    @Autowired
    OpenBankingService openBankingService;

    @Autowired
    LoanService loanService;

    public Object requestBankTransHistory(Integer lndCustomerId) {
        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while retrieving BankTransHistory");
            return null;
        } else {
            return openBankingService.requestBankTransHistory(lndCustomerId);
        }
    }

    public Object requestLoanHistory(Integer lndCustomerId) {
        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while retrieving LoanHistory");
            return null;
        } else {
            return openBankingService.requestLoanHistory(lndCustomerId);
        }
    }

    public Object requestLoanOffer (KafkaMessage message) {
        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while retrieving LoanOffer");
            return false;
        } else {

            LogUtil.logDebug("KafaProcessor : requestLoanOffer :" + message);

            int lndLoanId = message.getLoanId();
            double loanAmount = message.getLoanAmount();
            int customerId = message.getCustomerId();

            //Get LoanOffer from Simulator
            List loanOfferList = openBankingService.requestLoanOffer(customerId, lndLoanId, loanAmount);
            LogUtil.logDebug("KafaProcessor : requestLoanOffer : loanOfferList : " + loanOfferList);

            //Update LoanOffer from Bank in LndLoanOffer
            loanService.updateLoanOfferInDB(loanOfferList);

            LogUtil.logDebug("KafaProcessor : requestLoanOffer : completed");
            return true;
        }
    }

    public Object requestLoanDetail (KafkaMessage message) {
        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while retrieving LoanDetail");
            return false;
        } else {

            LogUtil.logDebug("KafaProcessor : requestLoanDetail :" + message);

            double loanAmount = message.getLoanAmount();
            int bankId = message.getBankId();
            int lndLoanId = message.getLoanId();
            int customerId = message.getCustomerId();

            //Get LoanDetail from Simulator
            LoanDetailInfo loanDetailInfo = openBankingService.requestLoanDetail(bankId, customerId, lndLoanId, loanAmount);
            LogUtil.logDebug("KafaProcessor : requestLoanDetail : loanDetailInfo : " + loanDetailInfo);

            //Update LoanDetail in DB
            loanService.updateLoanDetailInDB(loanDetailInfo);

            LogUtil.logDebug("KafaProcessor : requestLoanDetail : completed");
            return true;
        }
    }



}