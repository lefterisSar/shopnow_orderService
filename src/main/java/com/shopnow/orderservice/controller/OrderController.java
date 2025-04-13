package com.shopnow.orderservice.controller;

import com.shopnow.orderservice.dto.OrderRequest;
import com.shopnow.orderservice.entity.Order;
import com.shopnow.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order placeOrder(@RequestBody OrderRequest request, Authentication auth) {
        String email = auth.getName(); // comes from JWT
        return orderService.placeOrder(email, request.getTotal());
    }

    @GetMapping("/user")
    public List<Order> getUserOrders(Authentication auth) {
        return orderService.getOrdersForUser(auth.getName());
    }

    @GetMapping("/admin")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders(); // should be protected by role
    }
}
