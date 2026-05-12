package com.nuliyang.agent.controller;


import com.alibaba.cloud.ai.graph.exception.GraphRunnerException;
import com.nuliyang.agent.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/ai")
public class ChatController {

    @Autowired
    private ChatService chatService;


    @PostMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chat(@RequestParam("userQuery") String userQuery) throws GraphRunnerException {

        return chatService.chat(userQuery);
    }
}
