package com.example.interfazconodoo.Controladores;

import com.example.interfazconodoo.DAO.PaperformatDAO;
import com.example.interfazconodoo.Modelos.Paperformat;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class HelloController {

    @FXML
    private ChoiceBox select;
    @FXML
    private TableView tbDatos;
    @FXML
    private TableColumn flName;
    @FXML
    private TableColumn flFormat;
    @FXML
    private TableColumn flMarginLeft;
    @FXML
    private TableColumn flMarginRight;
    @FXML
    private TableColumn flMarginTop;
    @FXML
    private TableColumn flOrientation;
    @FXML
    private TableColumn flMarginButton;

    public void initialize() {
        select.getItems().addAll("Name", "Format", "Orientation");
        flName.setCellValueFactory(new PropertyValueFactory<>("name"));
        flFormat.setCellValueFactory(new PropertyValueFactory<>("format"));
        flOrientation.setCellValueFactory(new PropertyValueFactory<>("orientation"));
        flMarginTop.setCellValueFactory(new PropertyValueFactory<>("margin_top"));
        flMarginButton.setCellValueFactory(new PropertyValueFactory<>("margin_button"));
        flMarginRight.setCellValueFactory(new PropertyValueFactory<>("margin_right"));
        flMarginLeft.setCellValueFactory(new PropertyValueFactory<>("margin_left"));

    }

    @FXML
    public void onbBtnSearchMal(ActionEvent actionEvent) {
        try {
            List<Paperformat> formatos = PaperformatDAO.obtenerFormatos();
            ObservableList<Paperformat> datos = FXCollections.observableArrayList(formatos);
            tbDatos.setItems(datos);
        } catch (SQLException e) {
            System.err.println("Error al obtener los bancos: " + e.getMessage());
            // Manejar la excepción adecuadamente, por ejemplo, mostrando un mensaje de error al usuario
        }
    }

    @FXML
    public void onbBtnSearchBien(ActionEvent actionEvent) {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try{
                    List<Paperformat> formatos = PaperformatDAO.obtenerFormatos();
                    ObservableList<Paperformat> datos = FXCollections.observableArrayList(formatos);

                    Platform.runLater(() -> {
                        // Actualizar la interfaz gráfica con los valores de nombre y apellido
                        // Por ejemplo, añadirlos a un ListView, Label, etc.
                        tbDatos.setItems(datos);
                    });


                } catch (SQLException e) {
                    System.err.println("Error de SQL al consultar: " + e.getMessage());
                    Platform.runLater(() -> {

                    });
                }
                return null;
            }
        };

        Thread hilo = new Thread(tarea);
        hilo.start();
    }
}