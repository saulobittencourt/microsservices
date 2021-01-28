package com.ielop.store.model;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@Document
@Builder
public class Store {
    @Id
    private String id;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @CreatedBy
    private String username;

    @LastModifiedBy
    private String lastModifiedBy;

    @NonNull
    private String storeName;

    @NonNull
    private String storeAddress;

    private String imageUrl; //for now store will contain only one image

    private List<Product> products;

    private List<Order> orders;
}
