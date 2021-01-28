package com.ielop.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String id = new ObjectId().toString();
    private String name;
    private BigDecimal price;
}
