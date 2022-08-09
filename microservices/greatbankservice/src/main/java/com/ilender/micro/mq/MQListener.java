package com.ilender.micro.mq;

import com.ilender.micro.common.ConversionUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.model.BankTransHistoryRequestInfo;
import com.ilender.micro.model.LoanDetailRequestInfo;
import com.ilender.micro.model.LoanHistoryRequestInfo;
import com.ilender.micro.model.LoanOfferRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class MQListener {

    @Autowired
    MqProcessor mqProcessor;

    // Listen to queue
    @JmsListener(destination = "${ibm.mq.queueName.requestLoanOffer}")
    public void requestLoanOffer(final Message message) {
        LoanOfferRequestInfo info = (LoanOfferRequestInfo) convertMsg (message, LoanOfferRequestInfo.class);
        mqProcessor.loadLoanOffer(info);
    }

    // Listen to queue
    @JmsListener(destination = "${ibm.mq.queueName.requestLoanDetail}")
    public void requestLoanDetail(final Message message) {
        LoanDetailRequestInfo info = (LoanDetailRequestInfo) convertMsg (message, LoanDetailRequestInfo.class);
        mqProcessor.loadLoanDetail(info);
    }

    // Listen to queue
    @JmsListener(destination = "${ibm.mq.queueName.requestTransHistory}")
    public void requestTransHistory(final Message message) {
        BankTransHistoryRequestInfo info = (BankTransHistoryRequestInfo) convertMsg (message, BankTransHistoryRequestInfo.class);
        mqProcessor.bankTransHistory(info);
    }

    // Listen to queue
    @JmsListener(destination = "${ibm.mq.queueName.requestLoanHistory}")
    public void requestLoanHistory(final Message message) {
        LoanHistoryRequestInfo info = (LoanHistoryRequestInfo) convertMsg (message, LoanHistoryRequestInfo.class);
        mqProcessor.loanHistory(info);
    }

    private Object convertMsg(final Message message, Class returnClass) {
        LogUtil.logDebug("MQListener : convertMsg: started : " + message);
        LogUtil.logDebug("MQListener : convertMsg: returnClass : " + returnClass);

        String jsonString = ConversionUtil.jmsMessageInJson(message);
        LogUtil.logDebug("MQListener : convertMsg: Message Received JSON --->>>>: "+ jsonString);

        Object returnInfo = null;
        if (jsonString != null) {
            //Convert Json to MqMessage
            returnInfo = ConversionUtil.jsonToObject(jsonString, returnClass);
            LogUtil.logDebug("MQListener : convertMsg json message converted into Info object --->: " + returnInfo);
        }

        LogUtil.logDebug("MQListener : receiveMessageLoanOfferAccepted: completed  ");
        return returnInfo;
    }

}