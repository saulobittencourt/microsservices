package com.ielop.store.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface StoreEventStream {

    String OUTPUT = "momentsStoreChanged";

    @Output(OUTPUT)
    MessageChannel momentsStoreChanged();
}
