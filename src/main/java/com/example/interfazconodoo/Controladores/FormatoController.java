package com.example.interfazconodoo.Controladores;

import com.example.interfazconodoo.DAO.PaperformatDAO;
import com.example.interfazconodoo.Modelos.Paperformat;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormatoController {
    @javafx.fxml.FXML
    private TextField num_marginT;
    @javafx.fxml.FXML
    private TextField texto_name;
    @javafx.fxml.FXML
    private TextField num_marginB;
    @javafx.fxml.FXML
    private TextField num_marginR;
    @javafx.fxml.FXML
    private TextField num_marginL;
    @javafx.fxml.FXML
    private TextField texto_orientation;
    @javafx.fxml.FXML
    private TextField texto_format;
    @FXML
    private Button btCrear;

    private Paperformat formatoInicial;


    public void setFormatoNuevo(Paperformat Paperformat) {
        this.formatoInicial = Paperformat;
        cargarDatos();
    }

    private void cargarDatos(){

        texto_name.setText(formatoInicial.getName());
        texto_format.setText(formatoInicial.getFormat());
        texto_orientation.setText(formatoInicial.getOrientation());
        num_marginT.setText(String.valueOf(formatoInicial.getMarginTop()));
        num_marginB.setText(String.valueOf(formatoInicial.getMarginBottom()));
        num_marginL.setText(String.valueOf(formatoInicial.getMarginLeft()));
        num_marginR.setText(String.valueOf(formatoInicial.getMarginRight()));

        // Enlazar campos de texto con propiedades de `formatoInicial`
        formatoInicial.nameProperty().bindBidirectional(texto_name.textProperty());
        formatoInicial.formatProperty().bindBidirectional(texto_format.textProperty());
        formatoInicial.orientationProperty().bindBidirectional(texto_orientation.textProperty());
        Bindings.bindBidirectional(num_marginT.textProperty(), formatoInicial.marginTopProperty(), new javafx.util.converter.NumberStringConverter());
        Bindings.bindBidirectional(num_marginB.textProperty(), formatoInicial.marginBottomProperty(), new javafx.util.converter.NumberStringConverter());
        Bindings.bindBidirectional(num_marginL.textProperty(), formatoInicial.marginLeftProperty(), new javafx.util.converter.NumberStringConverter());
        Bindings.bindBidirectional(num_marginR.textProperty(), formatoInicial.marginRightProperty(), new javafx.util.converter.NumberStringConverter());
    }

    @FXML
    public void initialize() {
        // Binding reactivo para desactivar el botón `btCrear` hasta que todos los campos sean válidos
        BooleanBinding camposIncompletos = texto_name.textProperty().isEmpty()
                .or(texto_format.textProperty().isEmpty())
                .or(texto_orientation.textProperty().isEmpty())
                .or(num_marginT.textProperty().isEmpty())
                .or(num_marginB.textProperty().isEmpty())
                .or(num_marginL.textProperty().isEmpty())
                .or(num_marginR.textProperty().isEmpty());

        btCrear.disableProperty().bind(camposIncompletos);
    }


    @FXML
    public void onBtCrear(ActionEvent actionEvent) throws SQLException {
        // Aquí `formatoInicial` ya tiene todos los valores
        // actualizados en tiempo real gracias a los bindings
        try {
            if (formatoInicial != null) {
                // Si el formato existe, lo editamos
                PaperformatDAO.editarFormato(formatoInicial);
            } else {
                // Si es un nuevo formato, lo creamos
                PaperformatDAO.crearFormato(formatoInicial);
            }

            // Cerrar la ventana actual
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void onBtSalir(ActionEvent actionEvent) {

        // Cerrar la ventana sin guardar cambios
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

}
