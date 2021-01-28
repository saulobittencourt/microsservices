package com.ielop.store.repo;

import com.ielop.store.model.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StoreRepo extends MongoRepository<Store, String> {
    List<Store> findStoreByStoreNameLike(String storeName);
}