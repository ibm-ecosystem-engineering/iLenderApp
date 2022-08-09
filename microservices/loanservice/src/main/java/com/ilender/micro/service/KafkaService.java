package com.ilender.micro.service;

import com.ilender.micro.common.LogUtil;
import com.ilender.micro.entity.LndLoan;
import com.ilender.micro.kafka.KafkaMessageEmail;
import com.ilender.micro.kafka.KafkaSender;
import com.ilender.micro.model.CustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class KafkaService {

    @Value("${iLenderNotifyEnabled}")
    private boolean notifyEnabled;

    @Autowired
    UserService userService;

    @Autowired
    KafkaSender kafkaSender;

    public void notifyLoanRequestCreated(LndLoan loanInfo) {
        LogUtil.logDebug("KafkaService : notifyLoanRequestCreated : notifyEnabled " + notifyEnabled );
        if (notifyEnabled) {
            int customerId = loanInfo.getLndCustomerId();
            CustomerInfo customerInfo = userService.getCustomerInfo(customerId);
            KafkaMessageEmail mail =  getEmailForLoanRequestCreated(customerInfo, loanInfo);
            kafkaSender.notifyLoanRequestCreated(mail);
        }
    }

    private KafkaMessageEmail getEmailForLoanRequestCreated(CustomerInfo customerInfo, LndLoan loanInfo ) {
        String from = "admin@ilender12.com";
        String to = customerInfo.getEmailId();
        String cc = "jeyagandhi@in.ibm.com";
        String subject = "Loan Offer is Accepted by SMB";
        String body = "<<CUSTOMER>> has requested for the loan amount <<LoanAmount>> for <<Purpose>>";

        body = body.replace("<<CUSTOMER>>", customerInfo.getOrgName());
        body = body.replace("<<LoanAmount>>", loanInfo.getLoanAmount() + "");
        body = body.replace("<<Purpose>>", loanInfo.getPurpose() + "");

        KafkaMessageEmail email = new KafkaMessageEmail(from, to, cc, subject, body);
        return email;
    }

}