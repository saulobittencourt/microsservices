package com.ielop.order.service;

import com.ielop.order.messaging.OrderEventSender;
import com.ielop.order.model.Order;
import com.ielop.order.model.ProductOrder;
import com.ielop.order.model.enums.OrderStatus;
import com.ielop.order.model.enums.PaymentStatus;
import com.ielop.order.repo.OrderRepo;
import com.ielop.order.payload.OrderRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final OrderEventSender orderEventSender;

    public Order save(OrderRequest orderRequest){
        Order order = orderRepo.save(convertToOrder(orderRequest));

        // Send message to store telling that there is a new order

        // Send message to user telling the order was generated

        // Send message to origin telling the order data
        orderEventSender.sendOrderCreated(order);
        return order;
    }

    private Order convertToOrder(OrderRequest orderRequest) {
        BigDecimal total = null;
        for (ProductOrder productOrder: orderRequest.getProducts()) {
            total = productOrder.getProduct().getPrice().multiply(BigDecimal.valueOf(productOrder.getAmount()));
        }

        return Order.builder()
                .user(orderRequest.getUser())
                .store(orderRequest.getStore())
                .productOrder(orderRequest.getProducts())
                .paymentStatus(PaymentStatus.NOT_PAID)
                .orderStatus(OrderStatus.WAIT_STORE_START_ORDER)
                .total(total)
                .build();
    }
}
