package com.ilender.micro.mq;

import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.ilender.micro.common.ConversionUtil;
import com.ilender.micro.common.LogUtil;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@Service
public class MQSender {

    @Value("${ibm.mq.queueName.requestLoanOffer}")
    private String queueNameRequestLoanOffer;

    @Value("${ibm.mq.queueName.requestLoanDetail}")
    private String queueNameRequestLoanDetail;

    @Value("${ibm.mq.queueName.requestTransHistory}")
    private String queueNameRequestTransHistory;

    @Value("${ibm.mq.queueName.requestLoanHistory}")
    private String queueNameRequestLoanHistory;

    @Resource
    private JmsTemplate jmsTemplate;

    public void sendMessageRequestLoanOffer(Object messageInfo) {
        sendMsg (queueNameRequestLoanOffer, messageInfo);
    }
    public void sendMessageRequestLoanDetail(Object messageInfo) {
        sendMsg (queueNameRequestLoanDetail, messageInfo);
    }
    public void sendMessageRequestTransHistory(Object messageInfo) {
        sendMsg (queueNameRequestTransHistory, messageInfo);
    }
    public void sendMessageRequestLoanHistory(Object messageInfo) {
        sendMsg (queueNameRequestLoanHistory, messageInfo);
    }

    private void sendMsg(String queueName, Object messageInfo) {
        LogUtil.logDebug("MQSender : sendMsg : started : "+ queueName );
        LogUtil.logDebug("MQSender : sendMsg : messageInfo : "+ messageInfo );

        String jsonString = ConversionUtil.objectToJsonString(messageInfo);

        LogUtil.logDebug("MQSender : sendMsg : messageInfo converted to jsonString --->>>>: "+ jsonString);

        this.jmsTemplate.convertAndSend(queueName, jsonString);

        LogUtil.logDebug("MQSender : sendMsg : completed ");
    }
}