package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Representerar JSON-data på ett snyggt sätt.

@JsonIgnoreProperties(ignoreUnknown = true)
public record NtfyMessageDto(String id, long time, String event, String topic, String message) {
}