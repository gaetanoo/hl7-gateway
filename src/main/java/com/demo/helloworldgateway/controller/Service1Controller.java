package com.demo.helloworldgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service1Controller {
    @GetMapping("/service1/hello")
    public String hello() {
        return "Hello Service1";
    }
}
