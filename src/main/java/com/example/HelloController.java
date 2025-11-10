package com.example;

import javafx.event.ActionEvent;
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

    // 🔹 Här sparar vi senaste texten användaren skrev
    private String lastInput;

    @FXML
    private Label messageLabel;

    @FXML
    private void initialize() {
        if (messageLabel != null) {
            messageLabel.setText(model.getGreeting());
        }
        messageView.setItems(model.getMessages());
    }

    @FXML private TextField messageField;

    public void sendMessage(ActionEvent actionEvent) {
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
