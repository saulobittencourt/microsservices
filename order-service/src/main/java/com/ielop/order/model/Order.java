package com.ielop.order.model;

import com.ielop.order.model.enums.OrderStatus;
import com.ielop.order.model.enums.PaymentStatus;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private String id;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @CreatedBy
    private String username;

    @LastModifiedBy
    private String lastModifiedBy;

    @NonNull
    private User user;

    @NonNull
    private Store store;

    private List<ProductOrder> productOrder;

    private BigDecimal total;

    private OrderStatus orderStatus;

    private PaymentStatus paymentStatus;
}