package com.example;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


/**
 * Binder ihop View ↔ Model.
 */

public class HelloController {

    private final HelloModel model = new HelloModel(new NtfyConnectionImpl());
    public ListView<NtfyMessageDto> messageView;

    // Här sparar vi senaste texten användaren skrev
    private String lastInput;

    @FXML
    private Label messageLabel;

    @FXML
    private void initialize() {
        messageView.setItems(model.getMessages());

        new Thread(() -> {
            try {
                Thread.sleep(1000); // Vänta 1 sekund på att FX-tråden ska starta
            } catch (InterruptedException ignored) {}
            Platform.runLater(model::receiveMessage);
        }).start();
    }
    @FXML private TextField messageField;

    public void sendMessage() {
        // Läs text från inputfältet
        String inputText = messageField.getText().trim();

        //  Spara i variabel
        lastInput = inputText;

        //  Skriv ut till konsolen
        System.out.println(" Input saved: " + lastInput);

        //  (Valfritt) skicka till servern via model
        if (!inputText.isEmpty()) {
            model.setMessageToSend(inputText);
            model.sendMessage();
        }

        // Rensa fältet
        messageField.clear();
    }
}
