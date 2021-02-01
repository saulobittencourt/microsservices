package com.ielop.store.controller;

import com.ielop.store.model.Store;
import com.ielop.store.payload.StoreRequest;
import com.ielop.store.service.StoreService;
import com.ielop.store.util.Tools;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("store")
@AllArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<Store> save(@RequestBody StoreRequest storeRequest){
        Store store = storeService.save(storeRequest);

        Tools.createDir(store);

        return ResponseEntity.ok(store);
    }

    @GetMapping("/findbyname/{storeName}")
    public ResponseEntity<List<Store>> findUserLikeName(@PathVariable String storeName) {
        List<Store> stores = storeService.storesLikeStoreName(storeName);

        return ResponseEntity.ok(stores);
    }
}