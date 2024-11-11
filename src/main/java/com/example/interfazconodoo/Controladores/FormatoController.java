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

        // Verificar si el objeto formatoInicial está correctamente inicializado
        if (formatoInicial == null) {
            formatoInicial = new Paperformat();  // Inicializar si es null
        }

        // Asignar valores a formatoInicial si es nuevo o si es un formato existente
        String name = texto_name.getText();
        String formato = texto_format.getText();
        String orientation = texto_orientation.getText();
        Double margenT = parseDouble(num_marginT.getText());
        Double margenB = parseDouble(num_marginB.getText());
        Double margenL = parseDouble(num_marginL.getText());
        Double margenR = parseDouble(num_marginR.getText());

        // Si formatoInicial ya está inicializado, actualizamos sus propiedades
        formatoInicial.setName(name);
        formatoInicial.setFormat(formato);
        formatoInicial.setOrientation(orientation);
        formatoInicial.setMarginTop(margenT);
        formatoInicial.setMarginBottom(margenB);
        formatoInicial.setMarginLeft(margenL);
        formatoInicial.setMarginRight(margenR);

        // Crear o editar el formato en la base de datos
        if (formatoInicial.getId() == 0) {  // Si el ID es 0, significa que es un formato nuevo
            PaperformatDAO.crearFormato(formatoInicial);
        } else {
            PaperformatDAO.editarFormato(formatoInicial);
        }

        // Cerrar la ventana
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    // Metodo para evitar la excepción si el valor numerico no es valido.
    private Double parseDouble(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0.0;  // Retornar un valor por defecto en caso de error
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
