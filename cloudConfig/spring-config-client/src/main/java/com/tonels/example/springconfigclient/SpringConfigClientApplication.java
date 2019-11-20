package com.tonels.example.springconfigclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfigClientApplication.class, args);
	}
}

@RefreshScope
@RestController
class MessageRestController {

	@Value("${msg}")
	private String msg;
    @Value("${profile}")
    private String profile;

    @Value("${ls: 没发现此配置}")
    private String ls;


	@RequestMapping("/msg")
	String getMsg() {
		return this.msg;
	}

	@RequestMapping("/profile")
	String getProfile() {
		return this.profile;
	}

    @RequestMapping("/ls")
    String getLs() {
        return this.ls;
    }


}
