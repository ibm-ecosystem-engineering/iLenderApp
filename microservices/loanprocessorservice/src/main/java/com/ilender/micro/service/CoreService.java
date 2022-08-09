package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.LoadUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.kafka.KafkaMessage;
import com.ilender.micro.kafka.KafkaMessageEmail;
import com.ilender.micro.kafka.KafkaSender;
import com.ilender.micro.model.CustomerInfo;
import com.ilender.micro.model.LoanInfo;
import com.ilender.micro.model.LoanOfferInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Configuration
@Service
public class CoreService {

    private static final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGDNA_LOGGER);

    @Autowired
    LogService logService;

    @Autowired
    KafkaSender kafkaSender;

    @Autowired
    RestUtilService restUtilService;

    @Autowired
    LoanService loanService;

    @Autowired
    UserService userService;

    @Autowired
    OpenBankingService openBankingService;

    @Value("${iLenderNotifyEnabled}")
    private boolean notifyEnabled;

    @Value("${iLenderKafkaEnabled}")
    private boolean kafkaEnabled;

    public Object rejectLoanRequest (Integer lndLoanId) {
        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while rejectLoanRequest");
            return false;
        } else {
            //Update Status - Loan Request Rejected
            loanService.updateLoanRequestRejected(lndLoanId);

            return true;
        }
    }

    public Object approveLoanRequest (Integer lndLoanId) {
        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while approveLoanRequest");
            return false;
        } else {

            //Update Status - Loan Request Approved
            loanService.updateLoanRequestApproved(lndLoanId);

            //Get LoanInfo for customerId
            LoanInfo loanInfo = loanService.getLoanInfo(lndLoanId);
            int customerId = loanInfo.getLndCustomerId();
            double loanAmount = loanInfo.getLoanAmount();

            //Get CustomerInfo for emailid
            CustomerInfo customerInfo = userService.getCustomerInfo(customerId);

            //Notification message - Loan Request Approved
            if (notifyEnabled) {
                kafkaSender.notifyLoanRequestApproved(getEmailForLoanRequestApproved(customerInfo, loanInfo));
            }

            //Request for Loan Offer
            KafkaMessage kafkaMessage = new KafkaMessage(customerId, 0, lndLoanId, 0, loanAmount);
            if (kafkaEnabled) {
                kafkaSender.sendMessageToRequestLoanOffer(kafkaMessage);
            } else {
                openBankingService.requestLoanOffer(kafkaMessage);
            }
            return true;
        }
    }

    public Object acceptLoanOffer (Integer lndLoanOfferId) {
        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while acceptLoanOffer");
            return null;
        } else {

            //Update Status - Loan Request Approved
            loanService.updateLoanOfferAccepted(lndLoanOfferId);

            //Get LoanOfferInfo for loanAmount
            LoanOfferInfo loanOfferInfo = loanService.getLoanOfferInfo(lndLoanOfferId);

            if (loanOfferInfo == null) {
                LogUtil.logDebug("CoreService : acceptLoanOffer : loanOfferInfo is null for :" + lndLoanOfferId);
            } else {
                double loanAmount = loanOfferInfo.getLoanAmount();
                int lndLoanId = loanOfferInfo.getLndLoanId();
                int bankId = loanOfferInfo.getBankId();

                //Get LoanInfo for customerId
                LoanInfo loanInfo = loanService.getLoanInfo(lndLoanId);
                int customerId = loanInfo.getLndCustomerId();

                //Get CustomerInfo for emailid
                CustomerInfo customerInfo = userService.getCustomerInfo(customerId);

                //Notification message - Loan Offer Accepted
                if (notifyEnabled) {
                    kafkaSender.notifyLoanOfferAccepted(getEmailForOfferAccepted(customerInfo, loanOfferInfo));
                }

                //Request for Loan Detail
                KafkaMessage kafkaMessage = new KafkaMessage(customerId, bankId, lndLoanId, lndLoanOfferId, loanAmount);
                if (kafkaEnabled) {
                    kafkaSender.sendMessageToRequestLoanDetail(kafkaMessage);
                } else {
                    openBankingService.requestLoanDetail(kafkaMessage);
                }
            }
            return true;
        }
    }

    private KafkaMessageEmail getEmailForOfferAccepted(CustomerInfo customerInfo, LoanOfferInfo loanOfferInfo ) {
        String from = "admin@ilender12.com";
        String to = customerInfo.getEmailId();
        String cc = "jeyagandhi@in.ibm.com";
        String subject = "Loan Offer is Accepted by SMB";
        String body = "<<CUSTOMER>> has accepted the Loan Offer <<LoanAmount>> from the <<BANK>>";

        body = body.replace("<<CUSTOMER>>", customerInfo.getOrgName());
        body = body.replace("<<LoanAmount>>", loanOfferInfo.getLoanAmount() + "");
        body = body.replace(" <<BANK>>", loanOfferInfo.getBankName());

        KafkaMessageEmail email = new KafkaMessageEmail(from, to, cc, subject, body);
        return email;
    }

    private KafkaMessageEmail getEmailForLoanRequestApproved(CustomerInfo customerInfo, LoanInfo loanInfo ) {
        String from = "admin@ilender12.com";
        String to = customerInfo.getEmailId();
        String cc = "jeyagandhi@in.ibm.com";
        String subject = "Loan Offer is Accepted by SMB";
        String body = "Your Loan request for the purpose : <<Purpose>> : has approved by the BM.";

        body = body.replace("<<Purpose>>", loanInfo.getPurpose());

        KafkaMessageEmail email = new KafkaMessageEmail(from, to, cc, subject, body);
        return email;
    }
}