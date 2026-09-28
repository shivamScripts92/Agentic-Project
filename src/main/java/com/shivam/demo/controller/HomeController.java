package com.shivam.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(Authentication authentication) {

        if (authentication != null &&
                authentication.isAuthenticated()) {

            OAuth2AuthenticationToken oauthToken =
                    (OAuth2AuthenticationToken) authentication;

            String name = (String) oauthToken.getPrincipal()
                    .getAttributes()
                    .get("name");

            String email = (String) oauthToken.getPrincipal()
                    .getAttributes()
                    .get("email");

            return """
        <h1>Calendar Agent</h1>
        <h2>You are logged in!</h2>
        <p>Welcome, %s</p>
        <p>Email: %s</p>

        <form action="/agentic/ask" method="get">
            <input
                type="text"
                name="message"
                placeholder="Enter a calendar command..."
                size="50"
            />
            <button type="submit">Ask Calendar Agent</button>
        </form>
        """.formatted(name, email);
        }

        return """
            <h1>Calendar Agent</h1>
            <a href="/oauth2/authorization/google">
                Login with Google
            </a>
            """;
    }
}