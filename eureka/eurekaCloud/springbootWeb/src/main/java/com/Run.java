package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Run {

    public static void main(String[] args) {
        SpringApplication.run(Run.class);
    }

    @GetMapping("/")
    public String s1 (){
        return "sss";
    }


}
