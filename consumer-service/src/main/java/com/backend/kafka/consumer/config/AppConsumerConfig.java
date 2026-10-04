package com.backend.kafka.consumer.config;

import com.backend.kafka.consumer.model.OrderConsumerEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConsumerConfig {
    public AppConsumerConfig() {
        System.out.println(">>> KafkaConsumerConfig created");
    }

    @Bean
    public ConsumerFactory<String, OrderConsumerEvent> consumerFactory() {
        JacksonJsonDeserializer<OrderConsumerEvent> jsonDeserializer =
                new JacksonJsonDeserializer<>(OrderConsumerEvent.class);

        // Producer and consumer have different Java class names/packages.
        // Therefore, don't use the producer's __TypeId__ header.
        jsonDeserializer.setUseTypeHeaders(false);

        Map<String, Object> properties = new HashMap<>();

        properties.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092"
        );

        properties.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "order-consumer-group"
        );

        properties.put(
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                "earliest"
        );

        return new DefaultKafkaConsumerFactory<>(
                properties,
                new StringDeserializer(),
                jsonDeserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderConsumerEvent>
    kafkaListenerContainerFactory(
            ConsumerFactory<String, OrderConsumerEvent> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, OrderConsumerEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);

        return factory;
    }
}
