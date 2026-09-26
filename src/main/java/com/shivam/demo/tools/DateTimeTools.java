package com.shivam.demo.tools;

import org.springframework.ai.tool.annotation.Tool;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeTools {
    @Tool
    public String getCurrentDateTime() {
        return ZonedDateTime.now(ZoneId.of("Asia/Kolkata")).toString();
    }
}
