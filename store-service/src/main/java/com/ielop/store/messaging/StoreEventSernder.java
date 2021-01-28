package com.ielop.store.messaging;

import com.ielop.store.model.Store;
import com.ielop.store.payload.StoreEventPayload;
import lombok.AllArgsConstructor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StoreEventSernder {
    private final StoreEventStream storeEventStream;

    public void sendStoreCreated(Store store) {
        sendStoreChangedEvent(convertTo(store, StoreEventType.CREATED));
    }

    public void sendStoreUpdated(Store store) {
        sendStoreChangedEvent(convertTo(store, StoreEventType.UPDATED));
    }

    public void sendStoreDeleted(Store store) {
        sendStoreChangedEvent(convertTo(store, StoreEventType.DELETED));
    }

    private void sendStoreChangedEvent(StoreEventPayload payload) {

        Message<StoreEventPayload> message =
                MessageBuilder
                        .withPayload(payload)
                        .setHeader(KafkaHeaders.MESSAGE_KEY, payload.getId())
                        .build();

        storeEventStream.momentsStoreChanged().send(message);
    }

    private StoreEventPayload convertTo(Store store, StoreEventType eventType) {
        return StoreEventPayload
                .builder()
                .storeEventType(eventType)
                .id(store.getId())
                .username(store.getUsername())
                .createdAt(store.getCreatedAt())
                .updatedAt(store.getUpdatedAt())
                .lastModifiedBy(store.getLastModifiedBy())
                .imageUrl(store.getImageUrl())
                .storeName(store.getStoreName())
                .storeAddress(store.getStoreAddress())
                .products(store.getProducts())
                .build();
    }
}
