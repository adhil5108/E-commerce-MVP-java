package com.project.E_commerce.notification;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class ProductStream {

    private final Sinks.Many<String> sink =
            Sinks.many().multicast().directBestEffort();

    public void publish(String message) {
        sink.tryEmitNext(message);
    }

    public Flux<String> getStream() {
        return sink.asFlux();
    }
}
