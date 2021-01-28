package com.ielop.store.model;

import com.ielop.store.model.enums.OrderStatus;
import com.ielop.store.model.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private Store store;
    private List<ProductOrder> productOrder;

    private BigDecimal total;

    private OrderStatus orderStatus;

    private PaymentStatus paymentStatus;
}
