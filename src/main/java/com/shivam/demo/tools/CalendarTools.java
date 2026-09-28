package com.shivam.demo.tools;

import com.shivam.demo.service.CalendarService;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class CalendarTools {

    private final CalendarService calendarService;

    public CalendarTools(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @Tool(description = "Get the user's upcoming Google Calendar events")
    public String getUpcomingEvents(ToolContext toolContext) {

        String principalName =
                (String) toolContext.getContext().get("principalName");

        return calendarService.getUpcomingEvents(principalName);
    }

    @Tool(description = "Get details of a specific Google Calendar event using its event ID")
    public String getEvent(
            String eventId,
            ToolContext toolContext) {

        String principalName =
                (String) toolContext.getContext().get("principalName");

        return calendarService.getEvent(eventId, principalName);
    }

    @Tool(description = "Create a new Google Calendar event with a title, start date-time, and end date-time")
    public String createEvent(
            String summary,
            String startDateTime,
            String endDateTime,
            ToolContext toolContext) {

        String principalName =
                (String) toolContext.getContext().get("principalName");

        return calendarService.createEvent(
                summary,
                startDateTime,
                endDateTime,
                principalName
        );
    }

    @Tool(description = "Update an existing Google Calendar event")
    public String updateEvent(
            String eventId,
            String summary,
            String startDateTime,
            String endDateTime,
            ToolContext toolContext) {

        String principalName =
                (String) toolContext.getContext().get("principalName");

        return calendarService.updateEvent(
                eventId,
                summary,
                startDateTime,
                endDateTime,
                principalName
        );
    }

    @Tool(description = "Delete a Google Calendar event using its event ID")
    public String deleteEvent(
            String eventId,
            ToolContext toolContext) {

        String principalName =
                (String) toolContext.getContext().get("principalName");

        return calendarService.deleteEvent(
                eventId,
                principalName
        );
    }
}