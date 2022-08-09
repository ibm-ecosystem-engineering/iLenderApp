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

    @Value("${iLenderEventstreamTopicNotifyLoanRequestCreated}")
    private String topicNotifyLoanReuestCreated;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void notifyLoanRequestCreated(KafkaMessageEmail message) {
        LogUtil.logDebug("KafkaSender : notifyLoanRequestCreated :  " + message );

        sendMessage(topicNotifyLoanReuestCreated, message);

        LogUtil.logDebug("KafkaSender : notifyLoanRequestCreated : completed ");
    }

    private void sendMessage(String topicName, Object kafkaMessage) {
        LogUtil.logDebug("KafkaSender : sendMessage : topicName : " + topicName);
        LogUtil.logDebug("KafkaSender : sendMessage : kafkaMessage : " + kafkaMessage);

        String messageText = ConversionUtil.objectToJsonString(kafkaMessage);

        Message<String> message = MessageBuilder
                .withPayload(messageText)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        this.kafkaTemplate.send(message);

    }
}


