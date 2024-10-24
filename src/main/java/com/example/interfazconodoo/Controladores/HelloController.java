package com.example.interfazconodoo.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private ChoiceBox select;

    public void initialize() {
        select.getItems().addAll("Name", "Format", "Orientation");
    }


}