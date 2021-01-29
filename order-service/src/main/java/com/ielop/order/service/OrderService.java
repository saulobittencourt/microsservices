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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final OrderEventSender orderEventSender;

    @Transactional
    public Order save(OrderRequest orderRequest){
        Order order = orderRepo.save(convertToOrder(orderRequest));

        orderEventSender.sendOrderCreated(order);
        return order;
    }

    public List<Order> findOrdersByUser(String userId){
        return orderRepo.findOrderByUser_UserId(userId);
    }

    public List<Order> findOrdersByStore(String storeId){
        return orderRepo.findOrderByStore_StoreId(storeId);
    }

    private Order convertToOrder(OrderRequest orderRequest) {
        BigDecimal total = new BigDecimal(0);
        for (ProductOrder productOrder: orderRequest.getProducts()) {
            total = total.add(productOrder.getProduct().getPrice().multiply(BigDecimal.valueOf(productOrder.getAmount())));
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
