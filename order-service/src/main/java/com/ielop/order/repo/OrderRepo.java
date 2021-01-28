package com.ielop.order.repo;

import com.ielop.order.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<Order, String> {
}
