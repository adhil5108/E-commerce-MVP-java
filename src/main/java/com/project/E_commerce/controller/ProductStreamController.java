package com.project.E_commerce.controller;

import com.project.E_commerce.notification.ProductStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/products/notifications")
public class ProductStreamController {

    @Autowired
    private ProductStream productStream;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamProductNotifications() {
        return productStream.getStream();
    }
}
