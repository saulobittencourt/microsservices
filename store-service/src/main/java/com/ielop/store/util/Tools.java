package com.ielop.store.util;

import com.ielop.store.model.Store;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Tools {

    public static void createDir(Store store) {
        ServletUriComponentsBuilder.fromCurrentContextPath().path("/stores/{id}").buildAndExpand(store.getId()).toUri();
    }
}
