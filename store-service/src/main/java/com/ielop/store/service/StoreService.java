package com.ielop.store.service;

import com.ielop.store.messaging.StoreEventSernder;
import com.ielop.store.model.Store;
import com.ielop.store.payload.StoreRequest;
import com.ielop.store.repo.StoreRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StoreService {
    private final StoreRepo storeRepo;
    private final StoreEventSernder storeEventSernder;

    public Store save(StoreRequest storeRequest){
        Store store = Store.builder()
                .storeName(storeRequest.getStoreName())
                .storeAddress(storeRequest.getStoreAddress())
                .imageUrl(storeRequest.getImageUrl())
                .products(storeRequest.getProducts())
                .build();

        store = storeRepo.save(store);
        storeEventSernder.sendStoreCreated(store);

        return store;
    }

    public List<Store> storesLikeStoreName(String storeName) {
        return storeRepo.findStoreByStoreNameLike(storeName);
    }
}
