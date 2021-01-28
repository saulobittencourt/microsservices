package com.ielop.order.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderEventStream {
    String OUTPUT = "momentsOrderChanged";

    @Output(OUTPUT)
    MessageChannel momentsOrderChanged();
}
