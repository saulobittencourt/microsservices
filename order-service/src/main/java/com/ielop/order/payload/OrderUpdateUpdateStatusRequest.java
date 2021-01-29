package com.ielop.order.payload;

import com.ielop.order.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateUpdateStatusRequest {
    private String orderId;
    private OrderStatus orderStatus;
}
