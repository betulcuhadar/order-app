package com.example.order_service.controller;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order_service.model.Order;
import com.example.order_service.producer.KafkaProducerService;

import org.slf4j.Logger;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final KafkaProducerService producerService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


    public OrderController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        logger.info("Order here");
        producerService.sendOrder(order);
        return ResponseEntity.ok("Order sent to Kafka");
    }
}

