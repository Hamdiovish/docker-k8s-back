package com.example.restservice.dapr;

import com.example.restservice.heroes.controller.HeroesControllerV2;
import com.example.restservice.heroes.mapper.HeroMapper;
import com.example.restservice.heroes.model.Hero;
import com.example.restservice.heroes.model.HeroEntity;
import com.example.restservice.heroes.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
        logger.info("Received Message: " + new String(body));
        HeroEntity newHero = new HeroEntity(new String(body));
        if (newHero != null && !newHero.getName().isEmpty()) {
            logger.info("Saving " + newHero.toString());
            HeroEntity savedHero = heroRepository.save(new HeroEntity(newHero.getName()));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}