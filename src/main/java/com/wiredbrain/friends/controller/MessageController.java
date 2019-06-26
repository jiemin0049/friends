package com.wiredbrain.friends.controller;

import com.wiredbrain.friends.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @GetMapping("/message")
    Message send() {
        return new Message("first messge");
    }

    @PostMapping("/message")
    Message echo(@RequestBody Message message) {
        return message;
    }
}
