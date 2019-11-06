package com.tonels.microservice.example.bootiful.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class TestController {

    @GetMapping("/code")
    public String code(){
        return "Hello Code" ;
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "Hello Dashboard";
    }

    @GetMapping("/error")
    public ResponseEntity<?> error(){
        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Collections.singletonMap("error","could't take it anymore!"));
    }


}
