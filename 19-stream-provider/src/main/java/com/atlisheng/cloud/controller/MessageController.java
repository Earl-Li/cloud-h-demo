package com.atlisheng.cloud.controller;

import com.atlisheng.cloud.message.StreamMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    StreamMessageProvider streamMessageProvider;

    @GetMapping("/send")
    public String sendMessage(){
        return streamMessageProvider.send();
    }
}
