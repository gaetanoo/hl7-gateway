package com.demo.helloworldgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service2Controller {
    @GetMapping("/service2/hello")
    public String hello() {
        return "Hello Service 2";
    }

}
