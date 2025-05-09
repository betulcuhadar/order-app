package com.example.order_service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.order_service.config.KafkaConfig;
import com.example.order_service.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = KafkaConfig.TOPIC, groupId = "order-group")
    public void consumeOrder(String message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Order order = mapper.readValue(message, Order.class);
            System.out.println("Order received: " + order.getProduct() + ", " + order.getQuantity() + ", " + order.getPrice());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
