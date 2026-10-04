package com.backend.kafka.producer.model;

import java.math.BigDecimal;

public record OrderEvent(
        String orderId,
        String customerId,
        BigDecimal amount
) {
}
