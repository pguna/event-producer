package com.study.event_producer.controller;

import com.study.event_producer.model.Message;
import com.study.event_producer.service.MQService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

    private final MQService mqService;

    @GetMapping
    public Message getMessage() {
        final Message user = new Message();
        user.setCreatedAt(new Date());
        return user;
    }

    @PostMapping
    public Message createMessage(@RequestBody Message user) {
        return mqService.sendMessage(user);
    }

}
