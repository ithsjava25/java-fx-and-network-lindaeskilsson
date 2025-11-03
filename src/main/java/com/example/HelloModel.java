package com.example;

import javafx.scene.image.Image;

/**
 * Model layer: encapsulates application data and business logic.
 */
public class HelloModel {

    Image noSmash;
    Image smash1;
    Image smash2;
    Image smash3;
    Image smash4;

    public HelloModel() {
        noSmash = new Image(getClass().getResource("/lantern.png").toExternalForm());
    }
    public Image getNoSmash() {
        return noSmash;
    }

}
