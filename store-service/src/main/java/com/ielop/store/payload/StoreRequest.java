package com.ielop.store.payload;

import com.ielop.store.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequest {
    private String imageUrl;
    private String storeName;
    private String storeAddress;
    private List<Product> products;
}
