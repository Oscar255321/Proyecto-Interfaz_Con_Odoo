package com.example.interfazconodoo.Controladores;

import com.example.interfazconodoo.DAO.PaperformatDAO;
import com.example.interfazconodoo.Modelos.Paperformat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

    private Paperformat formatoInicial;

    public void setFormatoNuevo(Paperformat Paperformat) {
        this.formatoInicial = Paperformat;
        cargarDatos();
    }

    private void cargarDatos(){

        texto_name.setText(formatoInicial.getName());
        texto_format.setText(formatoInicial.getFormat());
        texto_orientation.setText(formatoInicial.getOrientation());
        num_marginT.setText(String.valueOf(formatoInicial.getMargin_top()));
        num_marginB.setText(String.valueOf(formatoInicial.getMargin_bottom()));
        num_marginL.setText(String.valueOf(formatoInicial.getMargin_left()));
        num_marginR.setText(String.valueOf(formatoInicial.getMargin_right()));
    }


    @FXML
    public void onBtCrear(ActionEvent actionEvent) throws SQLException {

        String name = texto_name.getText();
        System.out.println(name);
        String formato = texto_format.getText();
        System.out.println(formato);
        String orientation = texto_orientation.getText();
        System.out.println(orientation);
        Double margenT = Double.parseDouble(num_marginT.getText());
        System.out.println(margenT);
        Double margenB = Double.parseDouble(num_marginB.getText());
        System.out.println(margenB);
        Double margenL = Double.parseDouble(num_marginL.getText());
        System.out.println(margenL);
        Double margenR = Double.parseDouble(num_marginR.getText());
        System.out.println(margenR);

        if (formatoInicial != null) {

            formatoInicial.setName(name);
            System.out.println(formatoInicial);
            formatoInicial.setFormat(formato);
            System.out.println(formatoInicial);
            formatoInicial.setOrientation(orientation);
            System.out.println(formatoInicial);
            formatoInicial.setMargin_top(margenT);
            System.out.println(formatoInicial);
            formatoInicial.setMargin_bottom(margenB);
            System.out.println(formatoInicial);
            formatoInicial.setMargin_left(margenL);
            System.out.println(formatoInicial);
            formatoInicial.setMargin_right(margenR);
            System.out.println(formatoInicial);

            try
            {
                PaperformatDAO.editarFormato(formatoInicial);
                Node source = (Node) actionEvent.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        }else{

            Paperformat formatoNuevo = new Paperformat();

            formatoNuevo.setName(texto_name.getText());
            formatoNuevo.setFormat(texto_format.getText());
            formatoNuevo.setOrientation(texto_orientation.getText());
            formatoNuevo.setMargin_top(Double.parseDouble(num_marginT.getText()));
            formatoNuevo.setMargin_bottom(Double.parseDouble(num_marginB.getText()));
            formatoNuevo.setMargin_right(Double.parseDouble(num_marginR.getText()));
            formatoNuevo.setMargin_left(Double.parseDouble(num_marginL.getText()));

            try{
                PaperformatDAO.crearFormato(formatoNuevo);
                Node source = (Node) actionEvent.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            } catch (SQLException e){
                e.printStackTrace();
            }

        }
    }

    @javafx.fxml.FXML
    public void onBtSalir(ActionEvent actionEvent) {



    }


}
