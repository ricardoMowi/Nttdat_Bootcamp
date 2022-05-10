package com.nttdata.bootcamp.myfirstms.controller;

import com.nttdata.bootcamp.myfirstms.model.dto.RequestGet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequestMapping("/gretting")
public class MyFirstController {
    @GetMapping
    public Flux<RequestGet> get(){
        log.info("entrando a método get");
        RequestGet greeting = new RequestGet();
        greeting.setMessage("Hola mundo");
        return Flux.just(greeting);
    }
}
