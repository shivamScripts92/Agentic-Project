package com.shivam.demo.controller;

import com.shivam.demo.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/agentic")
public class ChatController {

    @Autowired
    ChatService chatService;
    @GetMapping("ask")
    public Flux<String> ask(String message) {
        return chatService.ask(message);
    }
    @GetMapping("/health")
    public String health(){
        return "active";
    }



}
