package com.ielop.store.payload;

import com.ielop.store.messaging.StoreEventType;
import com.ielop.store.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreEventPayload {

    private String id;
    private Instant createdAt;
    private Instant updatedAt;
    private String username;
    private String lastModifiedBy;
    private String storeName;
    private String storeAddress;
    private String imageUrl;
    private List<Product> products;
    private StoreEventType storeEventType;
}
