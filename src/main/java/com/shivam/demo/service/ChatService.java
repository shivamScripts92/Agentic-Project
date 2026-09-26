package com.shivam.demo.service;

import com.shivam.demo.tools.DateTimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
public class ChatService {

    ChatClient chatClient;

    @Autowired
    public ChatService(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultTools(new DateTimeTools())
                .build();
    }
    public Flux<String> ask(String message) {
    return chatClient.prompt()
               .user(message)

               .stream().content();

      // response;
    }
}
