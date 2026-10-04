package com.backend.kafka.consumer.service;

import com.backend.kafka.consumer.model.OrderConsumerEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumerService {

    public OrderConsumerService() {
        System.out.println(">>> OrderConsumer bean created");
    }

    @KafkaListener(
            topics = "orders",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(OrderConsumerEvent event) {
        System.out.println("Event consumed " + event);
    }
}
