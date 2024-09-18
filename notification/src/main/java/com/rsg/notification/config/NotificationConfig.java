package com.rsg.notification.config;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

public class NotificationConfig {
    private final KafkaProperties kafkaProperties;

    public NotificationConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory =
                new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
        return concurrentKafkaListenerContainerFactory;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        KafkaProperties.Consumer consumerProperties = kafkaProperties.getConsumer();
        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("bootstrap.servers", kafkaProperties.getBootstrapServers());
        propertyMap.put("group.id", consumerProperties.getGroupId());
        propertyMap.put("key.deserializer", consumerProperties.getKeyDeserializer());
        propertyMap.put("value.deserializer", consumerProperties.getValueDeserializer());
        return new DefaultKafkaConsumerFactory<>(propertyMap);

    }
}
