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

    @FXML
    public void onBtCrear(ActionEvent actionEvent) throws SQLException {

        Paperformat formato = new Paperformat();

        formato.setName(texto_name.getText());
        formato.setFormat(texto_format.getText());
        formato.setOrientation(texto_orientation.getText());
        formato.setMargin_top(Double.parseDouble(num_marginT.getText()));
        formato.setMargin_bottom(Double.parseDouble(num_marginB.getText()));
        formato.setMargin_right(Double.parseDouble(num_marginR.getText()));
        formato.setMargin_left(Double.parseDouble(num_marginL.getText()));
        
        PaperformatDAO.crearFormato(formato);
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @javafx.fxml.FXML
    public void onBtSalir(ActionEvent actionEvent) {

    }
}
