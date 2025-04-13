package com.shopnow.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String userEmail; // comes from JWT

    @Column(nullable = false)
    private String status; // e.g. PENDING, SHIPPED, etc.

    @CreationTimestamp
    private LocalDateTime createdAt;

    private BigDecimal total;
}
