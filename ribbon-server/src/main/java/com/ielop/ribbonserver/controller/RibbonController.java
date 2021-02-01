package com.ielop.ribbonserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1/api/ribbon")
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/message/big")
    public String showBigMessage(){
        String smallMessage = "";
        try {
            smallMessage = restTemplate.getForObject("http://localhost:8081/ribbon-server/message/small", String.class);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return smallMessage.concat(" How are you?");
    }

    @GetMapping("/message/small")
    public String showSmallMessage() {
        return "Hello!";
    }
}
