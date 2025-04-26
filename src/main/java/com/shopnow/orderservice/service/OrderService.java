package com.shopnow.orderservice.service;

import com.shopnow.orderservice.entity.Order;
import com.shopnow.orderservice.kafka.OrderEvent;
import com.shopnow.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class OrderService {


    private final KafkaTemplate<String, OrderEvent> kafkaTemplate; // ✅ Constructor injection

    private final OrderRepository orderRepository;

    public Order placeOrder(String userEmail, BigDecimal total) {
        Order order = Order.builder()
                .userEmail(userEmail)
                .status("PENDING")
                .total(total)
                .build();
        OrderEvent event = new OrderEvent(
                order.getId(),
                order.getUserEmail(),
                order.getStatus(),
                order.getTotal()
        );
        kafkaTemplate.send("order.created", event);
        return orderRepository.save(order);
    }

    public List<Order> getOrdersForUser(String email) {
        return orderRepository.findAllByUserEmail(email);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
