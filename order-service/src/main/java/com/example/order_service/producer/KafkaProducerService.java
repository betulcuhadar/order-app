package com.example.order_service.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.order_service.config.KafkaConfig;
import com.example.order_service.model.Order;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(Order order) {
        
        kafkaTemplate.send(KafkaConfig.TOPIC, order);
        System.out.println("Order sent: " + order.getProduct() + ", " + order.getQuantity() + ", " + order.getPrice());
    }
}

