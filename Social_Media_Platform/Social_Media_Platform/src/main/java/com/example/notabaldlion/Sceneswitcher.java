package com.example.notabaldlion;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class Sceneswitcher {
    public Sceneswitcher(String fxml)throws IOException {
        AnchorPane nextanchorpane= FXMLLoader.load(Objects.requireNonNull(main.class.getResource(fxml)));


    }
}
