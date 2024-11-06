package com.example.interfazconodoo.Controladores;

import com.example.interfazconodoo.DAO.PaperformatDAO;
import com.example.interfazconodoo.HelloApplication;
import com.example.interfazconodoo.Modelos.Paperformat;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HelloController {

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
    private Button btEditar;
    @FXML
    private Button btEliminar;
    @FXML
    private TextField texto_buscar;
    @FXML
    private TableColumn flMarginBotton;

    public void initialize() {

        flName.setCellValueFactory(new PropertyValueFactory<>("name"));
        flFormat.setCellValueFactory(new PropertyValueFactory<>("format"));
        flOrientation.setCellValueFactory(new PropertyValueFactory<>("orientation"));
        flMarginTop.setCellValueFactory(new PropertyValueFactory<>("margin_top"));
        flMarginBotton.setCellValueFactory(new PropertyValueFactory<>("margin_bottom"));
        flMarginRight.setCellValueFactory(new PropertyValueFactory<>("margin_right"));
        flMarginLeft.setCellValueFactory(new PropertyValueFactory<>("margin_left"));

    }

    @FXML
    public void onbBtnSearchBien(ActionEvent actionEvent) {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    List<Paperformat> formatos = PaperformatDAO.obtenerFormatos(texto_buscar.getText());
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



    @FXML
    public void onbBtnEditarFormat(ActionEvent actionEvent) throws IOException {

        Paperformat formatoInicial = (Paperformat) tbDatos.getSelectionModel().getSelectedItem();

        if (formatoInicial != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("nuevo_formato.fxml"));

                // Cargar la vista antes de obtener el controlador
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);

                // Obtener el controlador después de cargar la vista
                FormatoController formatoController = fxmlLoader.getController();
                formatoController.setFormatoNuevo(formatoInicial);

                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setTitle("Modificar Formato");
                stage.setScene(scene);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                stage.show();

            } catch (IOException e) {
                e.printStackTrace(); // Añadir esto para ver errores si ocurren
            }
        } else {
            System.err.println("Selecciona un formato por favor.");
        }
    }


    @FXML
    public void onbBtnEliminarFormat(ActionEvent actionEvent) throws IOException, SQLException {

        Paperformat borrar = (Paperformat) tbDatos.getSelectionModel().getSelectedItem();

        if (borrar == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Selecione un formato!!");
        }else{

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ADVERTENCIA!");
            alert.setHeaderText(null);
            alert.setContentText("¿Estas seguro que quieres eliminar el formato?");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {

                PaperformatDAO.eliminarFormato(borrar);
                onbBtnSearchBien(null);
            }
        }



    }

    @FXML
    public void onbBtnCrearFormat(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("nuevo_formato.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        scene.getStylesheets().add(HelloApplication.class.getResource("estilos.css").toExternalForm());

        FormatoController formatoController = fxmlLoader.getController();

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Crear Formato");
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }


}