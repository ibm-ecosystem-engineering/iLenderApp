package com.ilender.micro.kafka;

import com.ilender.micro.common.ConversionUtil;
import com.ilender.micro.common.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class KafkaSender {

    @Value("${iLenderEventstreamTopicRequestLoanOffer}")
    private String topicRequestLoanOffer;

    @Value("${iLenderEventstreamTopicRequestLoanDetail}")
    private String topicRequestLoanDetail;

    @Value("${iLenderEventstreamTopicNotifyLoanRequestApproved}")
    private String topicNotifyLoanRequestApproved;

    @Value("${iLenderEventstreamTopicNotifyLoanOfferAccepted}")
    private String topicNotifyLoanOfferAccepted;

    @Autowired(required=false)
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageToRequestLoanOffer(KafkaMessage kafkaMessage) {
        LogUtil.logDebug("KafkaSender : sendMessageToRequestLoanOffer : "+ kafkaMessage );
        sendMessage(topicRequestLoanOffer, kafkaMessage);
        LogUtil.logDebug("KafkaSender : sendMessageToRequestLoanOffer : completed ");
    }

    public void sendMessageToRequestLoanDetail(KafkaMessage kafkaMessage) {
        LogUtil.logDebug("KafkaSender : sendMessageToRequestLoanDetail : "+ kafkaMessage );
        sendMessage(topicRequestLoanDetail, kafkaMessage);
        LogUtil.logDebug("KafkaSender : sendMessageToRequestLoanDetail : completed  ");
    }

    public void notifyLoanRequestApproved(KafkaMessageEmail message) {
        LogUtil.logDebug("KafkaSender : notifyLoanRequestApproved :  " + message );
        sendMessage(topicNotifyLoanRequestApproved, message);
        LogUtil.logDebug("KafkaSender : notifyLoanRequestApproved : completed ");
    }

    public void notifyLoanOfferAccepted(KafkaMessageEmail message) {
        LogUtil.logDebug("KafkaSender : notifyLoanOfferAccepted : " + message );
        sendMessage(topicNotifyLoanOfferAccepted, message);
        LogUtil.logDebug("KafkaSender : notifyLoanOfferAccepted : completed ");
    }

    private void sendMessage(String topicName, Object kafkaMessage) {
        LogUtil.logDebug("KafkaSender : sendMessage : topicName : " + topicName);
        LogUtil.logDebug("KafkaSender : sendMessage : kafkaMessage : " + kafkaMessage);

        String messageText = ConversionUtil.objectToJsonString(kafkaMessage);
        LogUtil.logDebug("KafkaSender : sendMessage : messageText : " + messageText);

        Message<String> message = MessageBuilder
                .withPayload(messageText)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        this.kafkaTemplate.send(message);
    }
}


