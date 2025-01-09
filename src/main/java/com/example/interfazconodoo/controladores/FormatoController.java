package com.example.interfazconodoo.controladores;

import com.example.interfazconodoo.dao.Paperformatdao;
import com.example.interfazconodoo.modelos.Paperformat;
import java.sql.SQLException;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Clase para gestionar el funcionamiento de la interfaz de editar y crear formatos.
 *
 * @author Oscar Abellan
 * @version 1.0
 */

public class FormatoController {

  @javafx.fxml.FXML
  private TextField margint;
  @javafx.fxml.FXML
  private TextField textoname;
  @javafx.fxml.FXML
  private TextField marginb;
  @javafx.fxml.FXML
  private TextField marginr;
  @javafx.fxml.FXML
  private TextField marginl;
  @javafx.fxml.FXML
  private TextField textorientation;
  @javafx.fxml.FXML
  private TextField textoformat;
  @FXML
  private Button btcrear;

  private Paperformat formatoinicial;

  /**
   * Insertamos el nuevo formato o el modificado.
   */

  public void setFormatoNuevo(Paperformat paperformat) {
    this.formatoinicial = paperformat;
    cargarDatos();
  }

  /**
   * Cargamos los datos del formato.
   *
   * <p>Enlazamos los campos con con los campos de texto de la interfaz usando los binding.</p>
   */

  private void cargarDatos() {

    textoname.setText(formatoinicial.getName());
    textoformat.setText(formatoinicial.getFormat());
    textorientation.setText(formatoinicial.getOrientation());
    margint.setText(String.valueOf(formatoinicial.getMarginTop()));
    marginb.setText(String.valueOf(formatoinicial.getMarginBottom()));
    marginl.setText(String.valueOf(formatoinicial.getMarginLeft()));
    marginr.setText(String.valueOf(formatoinicial.getMarginRight()));

    formatoinicial.nameProperty().bindBidirectional(textoname.textProperty());
    formatoinicial.formatProperty().bindBidirectional(textoformat.textProperty());
    formatoinicial.orientationProperty().bindBidirectional(textorientation.textProperty());
    Bindings.bindBidirectional(margint.textProperty(), formatoinicial.marginTopProperty(),
        new javafx.util.converter.NumberStringConverter());
    Bindings.bindBidirectional(marginb.textProperty(), formatoinicial.marginBottomProperty(),
        new javafx.util.converter.NumberStringConverter());
    Bindings.bindBidirectional(marginl.textProperty(), formatoinicial.marginLeftProperty(),
        new javafx.util.converter.NumberStringConverter());
    Bindings.bindBidirectional(marginr.textProperty(), formatoinicial.marginRightProperty(),
        new javafx.util.converter.NumberStringConverter());
  }

  /**
   * Binding reactivo para desactivar el botón `btCrear` hasta que todos los campos sean válidos.
   */

  @FXML
  public void initialize() {
    // Binding reactivo para desactivar el botón `btCrear` hasta que todos los campos sean válidos
    BooleanBinding camposIncompletos = textoname.textProperty().isEmpty()
        .or(textoformat.textProperty().isEmpty())
        .or(textorientation.textProperty().isEmpty())
        .or(margint.textProperty().isEmpty())
        .or(marginb.textProperty().isEmpty())
        .or(marginl.textProperty().isEmpty())
        .or(marginr.textProperty().isEmpty());

    btcrear.disableProperty().bind(camposIncompletos);
  }

  /**
   * Creamos un elemento nuevo en la tabla de "report_paperformat" o editamos un elemento ya crado.
   *
   * <p>Tambien controlamos el problema de un formato inadecuadoi de un double.</p>
   */

  @FXML
  public void onBtCrear(ActionEvent actionEvent) throws SQLException {

    // Verificar si el objeto formatoinicial está correctamente inicializado
    if (formatoinicial == null) {
      formatoinicial = new Paperformat();  // Inicializar si es null
    }

    // Asignar valores a formatoinicial si es nuevo o si es un formato existente
    String name = textoname.getText();
    String formato = textoformat.getText();
    String orientation = textorientation.getText();
    Double margenT = parseDouble(margint.getText());
    Double margenB = parseDouble(marginb.getText());
    Double margenL = parseDouble(marginl.getText());
    Double margenR = parseDouble(marginr.getText());

    // Si formatoinicial ya está inicializado, actualizamos sus propiedades
    formatoinicial.setName(name);
    formatoinicial.setFormat(formato);
    formatoinicial.setOrientation(orientation);
    formatoinicial.setMarginTop(margenT);
    formatoinicial.setMarginBottom(margenB);
    formatoinicial.setMarginLeft(margenL);
    formatoinicial.setMarginRight(margenR);

    // Crear o editar el formato en la base de datos
    if (formatoinicial.getId() == 0) {  // Si el ID es 0, significa que es un formato nuevo
      Paperformatdao.crearFormato(formatoinicial);
    } else {
      Paperformatdao.editarFormato(formatoinicial);
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

  /**
   * Cerramos la ventana.
   */

  @javafx.fxml.FXML
  public void onBtSalir(ActionEvent actionEvent) {

    Node source = (Node) actionEvent.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();

  }

}
