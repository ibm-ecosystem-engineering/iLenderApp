package com.ilender.micro.config.kafka;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class MyKafkaProducerConfig {

    @Bean
    KafkaTemplate<String, Object> kafkaTemplate(){
        return new KafkaTemplate(producerFactory());
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory(){
        return createProducerFactory();
    }

    public ProducerFactory<String, Object> createProducerFactory(){
        String serverURL = MyKafkaParam.getServerURL();
        String trustStoreLocation =  MyKafkaParam.getTrustStoreLocation();
        String trustStorePassword =  MyKafkaParam.getTrustStorePassword();
        String sCRAMUser =  MyKafkaParam.getSCRAMUserName();
        String sCRAMPassword =  MyKafkaParam.getSCRAMPassword();

        Map<String, Object> config = new HashMap<>();
        config.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, serverURL);
        config.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        config.put(CommonClientConfigs.CONNECTIONS_MAX_IDLE_MS_CONFIG, 10000);
        config.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 30000);
        config.put(ProducerConfig.RETRIES_CONFIG, 0);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(SslConfigs.SSL_PROTOCOL_CONFIG, "TLSv1.2");
        config.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, trustStoreLocation);
        config.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, trustStorePassword);
        config.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-512");
        String saslJaasConfig = "org.apache.kafka.common.security.scram.ScramLoginModule required username=" + sCRAMUser + " password=" + sCRAMPassword + ";";
        config.put(SaslConfigs.SASL_JAAS_CONFIG, saslJaasConfig);

        return new DefaultKafkaProducerFactory(config);
    }
}
