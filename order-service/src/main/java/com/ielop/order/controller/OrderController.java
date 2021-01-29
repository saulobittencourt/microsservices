package com.ielop.order.controller;

import com.ielop.order.config.RibbonConfiguration;
import com.ielop.order.model.Order;
import com.ielop.order.payload.OrderRequest;
import com.ielop.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/orders")
@RibbonClient(name = "order-service", configuration = RibbonConfiguration.class)
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody OrderRequest orderRequest){
        return ResponseEntity.ok(orderService.save(orderRequest));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> findOrdersByUser(@PathVariable String userId){
        return ResponseEntity.ok(orderService.findOrdersByUser(userId));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<Order>> findOrdersByStore(@PathVariable String storeId){
        return ResponseEntity.ok(orderService.findOrdersByStore(storeId));
    }
}
