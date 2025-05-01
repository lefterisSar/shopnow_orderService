package com.shopnow.orderservice.controller;

import com.shopnow.orderservice.dto.OrderRequest;
import com.shopnow.orderservice.entity.Order;
import com.shopnow.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
        String email = auth.getName();
        return orderService.placeOrder(email, request.getTotal());
    }

    @GetMapping("/user")
    public List<Order> getUserOrders(Authentication auth) {
        return orderService.getOrdersForUser(auth.getName());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}
