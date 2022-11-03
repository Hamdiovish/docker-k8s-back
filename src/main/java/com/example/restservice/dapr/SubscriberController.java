package com.example.restservice.dapr;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class SubscriberController {
    private static final Logger log = LoggerFactory.getLogger(SubscriberController.class);
    @PostMapping(path = "/sample")
    public Mono<String> getCheckout(@RequestBody(required = false) byte[] body) {
        return Mono.fromRunnable(() ->
                log.info("Received Message: " + new String(body)));
    }
    @PostMapping(path = "/sample-pubsub")
    public Mono<String> getPubSubCheckout(@RequestBody(required = false) byte[] body) {
        return Mono.fromRunnable(() ->
                log.info("Received PubSub Message: " + new String(body)));
    }
}