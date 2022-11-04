package com.example.restservice.dapr.controller;

import com.example.restservice.dapr.dto.MessageDTO;
import com.example.restservice.heroes.mapper.HeroMapper;
import com.example.restservice.heroes.model.HeroEntity;
import com.example.restservice.heroes.repository.HeroRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class SubscriberController {
    private static final Logger logger = LoggerFactory.getLogger(SubscriberController.class);

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private HeroMapper heroMapper;

    @PostMapping(path = "/sample")
    public Mono<String> getCheckout(@RequestBody(required = false) byte[] body) {
        return Mono.fromRunnable(() ->
                logger.info("Received Message: " + new String(body)));
    }
    @PostMapping(path = "/sample-pubsub")
    public ResponseEntity<Void> getPubSubCheckout(@RequestBody(required = false) byte[] body) {
        String input = new String(body);
        logger.info("Received Message: " + input);
        Gson g = new Gson();
        MessageDTO msg = g.fromJson(input, MessageDTO.class);
        HeroEntity newHero = new HeroEntity(msg.getData());
        if (newHero != null && !newHero.getName().isEmpty()) {
            logger.info("Saving " + newHero.toString());
            HeroEntity savedHero = heroRepository.save(newHero);
            return ResponseEntity.ok().build();
        }else{
            logger.info("New hero is ignored: " + newHero.getName());
        }
        return ResponseEntity.badRequest().build();
    }
}