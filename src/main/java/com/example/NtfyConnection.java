package com.example;

import java.util.function.Consumer;

//Gränssnitt + implementation → visar Dependency Inversion.

public interface NtfyConnection {

    public boolean send(String message);

    public void receive(Consumer<NtfyMessageDto> messageHandler);

}