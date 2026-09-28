package com.shivam.demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		//System.out.println("CLIENT ID = " + System.getenv("GOOGLE_CLIENT_ID"));
		SpringApplication.run(DemoApplication.class, args);

	}
	@Bean
	public ChatClient chatClient(ChatClient.Builder builder) {
		return builder.build();
	}

}
