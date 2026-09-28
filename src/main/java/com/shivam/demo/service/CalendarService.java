package com.shivam.demo.service;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class CalendarService {

    private final OAuth2AuthorizedClientService authorizedClientService;
    private final WebClient webClient;

    public CalendarService(
            OAuth2AuthorizedClientService authorizedClientService,
            WebClient.Builder webClientBuilder) {

        this.authorizedClientService = authorizedClientService;
        this.webClient = webClientBuilder.build();
    }

    public String getAccessToken(String principalName) {

        OAuth2AuthorizedClient client =
                authorizedClientService.loadAuthorizedClient(
                        "google",
                        principalName
                );

        return client.getAccessToken().getTokenValue();
    }

    public String getUpcomingEvents(String principalName) {

        String accessToken = getAccessToken(principalName);

        return webClient.get()
                .uri("https://www.googleapis.com/calendar/v3/calendars/primary/events")
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String getEvent(
            String eventId,
            String principalName) {

        String accessToken = getAccessToken(principalName);

        return webClient.get()
                .uri(
                        "https://www.googleapis.com/calendar/v3/calendars/primary/events/{eventId}",
                        eventId
                )
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String createEvent(
            String summary,
            String startDateTime,
            String endDateTime,
            String principalName) {

        String accessToken = getAccessToken(principalName);

        Map<String, Object> event = Map.of(
                "summary", summary,
                "start", Map.of(
                        "dateTime", startDateTime,
                        "timeZone", "Asia/Kolkata"
                ),
                "end", Map.of(
                        "dateTime", endDateTime,
                        "timeZone", "Asia/Kolkata"
                )
        );

        return webClient.post()
                .uri("https://www.googleapis.com/calendar/v3/calendars/primary/events")
                .header("Authorization", "Bearer " + accessToken)
                .bodyValue(event)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String updateEvent(
            String eventId,
            String summary,
            String startDateTime,
            String endDateTime,
            String principalName) {

        String accessToken = getAccessToken(principalName);

        Map<String, Object> event = Map.of(
                "summary", summary,
                "start", Map.of(
                        "dateTime", startDateTime,
                        "timeZone", "Asia/Kolkata"
                ),
                "end", Map.of(
                        "dateTime", endDateTime,
                        "timeZone", "Asia/Kolkata"
                )
        );

        return webClient.patch()
                .uri(
                        "https://www.googleapis.com/calendar/v3/calendars/primary/events/{eventId}",
                        eventId
                )
                .header("Authorization", "Bearer " + accessToken)
                .bodyValue(event)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String deleteEvent(
            String eventId,
            String principalName) {

        String accessToken = getAccessToken(principalName);

        webClient.delete()
                .uri(
                        "https://www.googleapis.com/calendar/v3/calendars/primary/events/{eventId}",
                        eventId
                )
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .toBodilessEntity()
                .block();

        return "Calendar event deleted successfully.";
    }
}