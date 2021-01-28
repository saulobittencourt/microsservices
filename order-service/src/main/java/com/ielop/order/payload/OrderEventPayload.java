package com.ielop.order.payload;

import com.ielop.order.messaging.OrderEventType;
import com.ielop.order.model.ProductOrder;
import com.ielop.order.model.enums.OrderStatus;
import com.ielop.order.model.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEventPayload {
    private String id;
    private Instant createdAt;
    private Instant updatedAt;
    private List<ProductOrder> productOrders;
    private BigDecimal total;

    private OrderStatus orderStatus;

    private PaymentStatus paymentStatus;

    private OrderEventType orderEventType;
}
