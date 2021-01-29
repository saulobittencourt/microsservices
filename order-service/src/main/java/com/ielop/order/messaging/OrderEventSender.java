package com.ielop.order.messaging;

import com.ielop.order.model.Order;
import com.ielop.order.payload.OrderEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderEventSender {
    private final OrderEventStream orderEventStream;

    public void sendOrderCreated(Order order) {
        sendOrderChangedEvent(convertTo(order, OrderEventType.CREATED));
    }

    private void sendOrderChangedEvent(OrderEvent payload) {
        Message<OrderEvent> message =
                MessageBuilder
                        .withPayload(payload)
                        .setHeader(KafkaHeaders.MESSAGE_KEY, payload.getId())
                        .build();

        orderEventStream.momentsOrderChanged().send(message);
    }

    private OrderEvent convertTo(Order order, OrderEventType eventType) {
        return OrderEvent.builder()
                .orderEventType(eventType)
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .orderStatus(order.getOrderStatus())
                .paymentStatus(order.getPaymentStatus())
                .productOrders(order.getProductOrder())
                .total(order.getTotal())
                .build();
    }
}
