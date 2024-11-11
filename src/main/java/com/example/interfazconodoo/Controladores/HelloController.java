
package com.example.interfazconodoo.Controladores;

import com.example.interfazconodoo.HelloApplication;
import com.example.interfazconodoo.Modelos.Paperformat;
import com.example.interfazconodoo.DAO.PaperformatDAO;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    private TableView<Paperformat> tbDatos;
    @FXML
    private TableColumn<Paperformat, String> flName;
    @FXML
    private TableColumn<Paperformat, String> flFormat;
    @FXML
    private TableColumn<Paperformat, Double> flMarginLeft;
    @FXML
    private TableColumn<Paperformat, Double> flMarginRight;
    @FXML
    private TableColumn<Paperformat, Double> flMarginTop;
    @FXML
    private TableColumn<Paperformat, String> flOrientation;
    @FXML
    private TableColumn<Paperformat, Double> flMarginBotton;
    @FXML
    private Button btEditar;
    @FXML
    private Button btEliminar;
    @FXML
    private TextField texto_buscar;

    private ObservableList<Paperformat> datos = FXCollections.observableArrayList();

    public void initialize() {
        // Asignar valores de celda para cada columna, usando los nombres de propiedad de Paperformat
        flName.setCellValueFactory(new PropertyValueFactory<>("name"));
        flFormat.setCellValueFactory(new PropertyValueFactory<>("format"));
        flOrientation.setCellValueFactory(new PropertyValueFactory<>("orientation"));
        flMarginTop.setCellValueFactory(new PropertyValueFactory<>("marginTop"));
        flMarginBotton.setCellValueFactory(new PropertyValueFactory<>("marginBottom"));
        flMarginRight.setCellValueFactory(new PropertyValueFactory<>("marginRight"));
        flMarginLeft.setCellValueFactory(new PropertyValueFactory<>("marginLeft"));

        tbDatos.setItems(datos);

        // Binding reactivo para desactivar btEliminar y btEditar si no hay selección
        BooleanBinding noSelection = tbDatos.getSelectionModel().selectedItemProperty().isNull();
        btEditar.disableProperty().bind(noSelection);
        btEliminar.disableProperty().bind(noSelection);

        // Binding para desactivar el botón de búsqueda si el campo está vacío
        btEliminar.disableProperty().bind(Bindings.isEmpty(texto_buscar.textProperty()));

        // Detectar cambios en el campo de búsqueda y aplicar el filtro reactivo
        texto_buscar.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty()) {
                datos.clear(); // Limpiar la lista si el campo de búsqueda está vacío
            } else {
                onbBtnSearchBien(null); // Llamar al método de búsqueda cada vez que cambia el texto
            }
        });
    }

    @FXML
    public void onbBtnSearchBien(ActionEvent actionEvent) {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    List<Paperformat> formatos = PaperformatDAO.obtenerFormatos(texto_buscar.getText());
                    ObservableList<Paperformat> datos = FXCollections.observableArrayList(formatos);

                    Platform.runLater(() -> tbDatos.setItems(datos));

                } catch (SQLException e) {
                    System.err.println("Error de SQL al consultar: " + e.getMessage());
                }
                return null;
            }
        };
        Thread hilo = new Thread(tarea);
        hilo.start();
    }

    @FXML
    public void onbBtnEditarFormat(ActionEvent actionEvent) throws IOException {
        Paperformat formatoInicial = tbDatos.getSelectionModel().getSelectedItem();

        if (formatoInicial != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("nuevo_formato.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);

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
                e.printStackTrace();
            }
        } else {
            System.err.println("Selecciona un formato por favor.");
        }
    }

    @FXML
    public void onbBtnEliminarFormat(ActionEvent actionEvent) throws IOException, SQLException {
        Paperformat borrar = tbDatos.getSelectionModel().getSelectedItem();

        if (borrar == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione un formato!!");
            alert.showAndWait();
        } else {
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
        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }
}
