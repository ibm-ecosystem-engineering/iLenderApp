package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.LoadUtil;
import com.ilender.micro.model.LoanDetailInfo;
import com.ilender.micro.model.LoanDetailRequestInfo;
import com.ilender.micro.model.LoanOfferRequestInfo;
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
    OpenBankingSimulatorService openBankingSimulatorService;

    public List loadLoanOffer(LoanOfferRequestInfo entity) {

        logService.logInfo(logger, "LoanOffer Retrieve started");

        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while Generating LoanOffer");
            return null;
        } else{
            int loanId = entity.getLoanId();
            double loanAmount = entity.getLoanAmount();
            List result = openBankingSimulatorService.loadLoanOffer(loanId, loanAmount);

            logService.logInfo(logger, "LoanOffer Generated for loan : " + entity.getLoanId());

            return result;
        }

    }

    public LoanDetailInfo loadLoanDetail(LoanDetailRequestInfo entity) {
        logService.logInfo(logger, "LoanDetail Retrieve started");

        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while Generating LoanDetail");
            return null;
        } else {
            int loanId = entity.getLoanId();
            double loanAmount = entity.getLoanAmount();
            LoanDetailInfo result = openBankingSimulatorService.loadLoanDetail(loanId, loanAmount);

            logService.logInfo(logger, "LoanDetail Generated for loan : " + entity.getLoanId());

            return result;
        }
    }

}