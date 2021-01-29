package com.ielop.order.repo;

import com.ielop.order.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepo extends MongoRepository<Order, String> {

    List<Order> findOrderByUser_UserId(String userId);

    List<Order> findOrderByStore_StoreId(String storeId);
}
