package com.shivam.demo.service;

import com.shivam.demo.tools.CalendarTools;
import com.shivam.demo.tools.DateTimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
public class ChatService {

    ChatClient chatClient;

    @Autowired
    public ChatService(
            ChatClient.Builder builder,
            CalendarTools calendarTools) {

        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultTools(
                        new DateTimeTools(),
                        calendarTools
                )
                .build();
    }

    public Flux<String> ask(String message, String principalName) {

        return chatClient.prompt()
                .user(message)
                .toolContext(Map.of("principalName", principalName))
                .stream()
                .content();
    }
}