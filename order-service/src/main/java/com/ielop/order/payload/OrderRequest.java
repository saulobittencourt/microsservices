package com.ielop.order.payload;

import com.ielop.order.model.ProductOrder;
import com.ielop.order.model.Store;
import com.ielop.order.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private User user;
    private Store store;
    private List<ProductOrder> products;
}
