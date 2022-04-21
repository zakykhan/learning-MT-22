package com.spring.junit.controller;

import com.spring.junit.model.Alien;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alien")
public class AlienController {

    @GetMapping("/1")
    public Alien getAlien() {

        return Alien.builder().
                alienId("01212").
                alienName("Light yagami").
                species("Yato Clan").
                alienAge("190")
                .build();
    }

}
