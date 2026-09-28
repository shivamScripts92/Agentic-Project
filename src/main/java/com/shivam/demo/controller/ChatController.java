package com.shivam.demo.controller;

import com.shivam.demo.service.CalendarService;
import com.shivam.demo.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/agentic")
public class ChatController {

    @Autowired
    ChatService chatService;
    @Autowired
    CalendarService calendarService;


    @GetMapping("/ask")
    public Flux<String> ask(
            String message,
            Authentication authentication) {

        return chatService.ask(message, authentication.getName());
    }

    @GetMapping("/")
    public String home() {
        return """
            <h1>Calendar Agent</h1>
            <a href="/oauth2/authorization/google">
                Login with Google
            </a>
            """;
    }


    @GetMapping("/health")
    public String health(){
        return "active";
    }



}
