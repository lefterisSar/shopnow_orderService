package com.shopnow.orderservice.consumer;

import com.shopnow.orderservice.kafka.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderNotificationConsumer {

    @KafkaListener(topics = "order.created", groupId = "notification-service-group")
    public void consume(OrderEvent event) {
        System.out.println("✅ [Notification] New order placed for user: " + event.getUserEmail());
        System.out.println("🛒 Total Amount: $" + event.getTotal());
        System.out.println("📦 Status: " + event.getStatus());
        System.out.println("-------------------------------");
    }
}
