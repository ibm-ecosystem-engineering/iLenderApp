package com.ilender.micro.kafka;

import com.ilender.micro.common.ConversionUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Configuration
@Service
public class KafkaReceiver {

    @Autowired
    CoreService coreService;

    @KafkaListener(topics = "${iLenderEventstreamTopicRequestLoanOffer}", autoStartup="${iLenderKafkaEnabled}")
    public void receiveMessageToRequestLoanOffer(@Payload List<String> list) {
        LogUtil.logDebug("KafkaReceiver : sendMessageToRequestLoanOffer :  "+ list );

        KafkaMessage kafkaMessage = receiveMessage(list);
        if (kafkaMessage != null) {
            coreService.requestLoanOffer(kafkaMessage);
        }

        LogUtil.logDebug("KafkaReceiver : sendMessageToRequestLoanOffer : completed ");
    }

    @KafkaListener(topics = "${iLenderEventstreamTopicRequestLoanDetail}", autoStartup="${iLenderKafkaEnabled}")
    public void receiveMessageToRequestLoanDetail(@Payload List<String> list) {
        LogUtil.logDebug("KafkaReceiver : receiveMessageToRequestLoanDetail : "+ list );

        KafkaMessage kafkaMessage = receiveMessage(list);
        if (kafkaMessage != null) {
            coreService.requestLoanDetail(kafkaMessage);
        }

        LogUtil.logDebug("KafkaReceiver : receiveMessageToRequestLoanDetail : completed ");
    }

    private KafkaMessage receiveMessage(@Payload List<String> list) {
        LogUtil.logDebug("KafkaReceiver : receiveMessage 1: " + list );

        KafkaMessage kafkaMessage = null;
        if (list == null || list.size() ==0) {
            LogUtil.logDebug("KafkaReceiver : receiveMessage 2: Message is Empty ");
        } else {
            String jsonString = list.get(0);
            LogUtil.logDebug("KafkaReceiver : receiveMessage 3: Message jsonString is : ---> " + jsonString);

            kafkaMessage = (KafkaMessage) ConversionUtil.jsonToObject(jsonString, KafkaMessage.class);

            LogUtil.logDebug("KafkaReceiver : receiveMessage 4: Message kafkaMessage is : ---> " + kafkaMessage);
        }

        LogUtil.logDebug("KafkaReceiver : receiveMessage : completed ");
        return kafkaMessage;
    }

}