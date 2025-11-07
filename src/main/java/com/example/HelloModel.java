package com.example;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

/**
 * Model layer: encapsulates application data and business logic.
 */
public class HelloModel {
    /**
     * Returns a greeting based on the current Java and JavaFX versions.
     */
    private final String hostName;

    public HelloModel() {
        Dotenv dotenv = Dotenv.load();
        hostName = Objects.requireNonNull(dotenv.get("NTFY_TOPIC")).trim();
    }
    public String getGreeting() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        return "Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".";
    }

    public void sendMessage() {
        // todo: send messege using HTTPclient
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest httpRequest = (HttpRequest) HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString("My first messege!"))
                .uri(URI.create(hostName + "/mytopic"))
                .build();
        try {
            //todo: handle long blocking send request to not freeze the javaFX thread
            //1. use thread send messege?
            //2. use async?
            var response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.out.println("Error sending messege");
        } catch (InterruptedException e) {
            System.out.println("interupded sending messege");
        }
    }
    }

