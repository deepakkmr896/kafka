package com.backend.kafka.consumer.model;

import java.math.BigDecimal;

public record OrderConsumerEvent(
        String orderId,
        String customerId,
        BigDecimal amount
) {
}
