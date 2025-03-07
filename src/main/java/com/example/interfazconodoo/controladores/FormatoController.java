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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para gestionar la interfaz gráfica de creación y edición de formatos.
 *
 * <p>Esta clase permite cargar, modificar y almacenar información sobre formatos de papel,
 * gestionando los datos ingresados por el usuario y validando
 * la información antes de guardarla.</p>
 *
 * <p>Incluye bindings bidireccionales entre los campos de la interfaz y las propiedades del objeto
 * {@link Paperformat}, asegurando la sincronización de datos en tiempo real.</p>
 *
 * @author Oscar Abellan
 * @version 1.1
 */

public class FormatoController {

  private Paperformat formatoinicial;
  @FXML
  private Label tfTitulo;
  @FXML
  private TextField marginb;
  @FXML
  private TextField marginr;
  @FXML
  private TextField margint;
  @FXML
  private TextField textoformat;
  @FXML
  private TextField textorientation;
  @FXML
  private TextField marginl;
  @FXML
  private TextField textoname;
  @FXML
  private Button btsalir;
  @FXML
  private Button btcrear;

  /**
   * Establece un nuevo formato de papel para su edición o visualización.
   *
   * @param paperformat El objeto {@link Paperformat} que contiene los datos del formato.
   */

  public void setFormatoNuevo(Paperformat paperformat) {
    this.formatoinicial = paperformat;
    cargarDatos();
  }

  /**
   * Carga los datos del formato seleccionado en los campos de la interfaz.
   *
   * <p>Asigna los valores correspondientes y establece bindings bidireccionales entre
   * las propiedades del objeto {@link Paperformat} y los campos de texto.</p>
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
   * Inicializa el controlador y configura la validación de los campos de entrada.
   *
   * <p>Desactiva el botón de creación hasta que todos los campos obligatorios tengan valores.</p>
   */

  @FXML
  public void initialize() {

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
   * Guarda un nuevo formato o actualiza uno existente en la base de datos.
   *
   * <p>Si el objeto {@link Paperformat} no tiene un ID asignado, se creará un nuevo registro.
   * En caso contrario, se actualizará el registro existente. Se maneja la validación de los
   * valores ingresados para evitar errores de formato.</p>
   *
   * @param actionEvent Evento de la interfaz que dispara la acción.
   * @throws SQLException Si ocurre un error durante la operación en la base de datos.
   */

  @FXML
  public void onBtCrear(ActionEvent actionEvent) throws SQLException {


    if (formatoinicial == null) {
      formatoinicial = new Paperformat();  // Inicializar si es null
    }


    String name = textoname.getText();
    String formato = textoformat.getText();
    String orientation = textorientation.getText();
    Double margenT = parseDouble(margint.getText());
    Double margenB = parseDouble(marginb.getText());
    Double margenL = parseDouble(marginl.getText());
    Double margenR = parseDouble(marginr.getText());


    formatoinicial.setName(name);
    formatoinicial.setFormat(formato);
    formatoinicial.setOrientation(orientation);
    formatoinicial.setMarginTop(margenT);
    formatoinicial.setMarginBottom(margenB);
    formatoinicial.setMarginLeft(margenL);
    formatoinicial.setMarginRight(margenR);


    if (formatoinicial.getId() == 0) {
      Paperformatdao.crearFormato(formatoinicial);
    } else {
      Paperformatdao.editarFormato(formatoinicial);
    }

    Node source = (Node) actionEvent.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }

  /**
   * Convierte un texto en un número decimal, manejando posibles errores de formato.
   *
   * @param text Texto a convertir.
   * @return El valor numérico si es válido, o 0.0 en caso de error.
   */

  private Double parseDouble(String text) {
    try {
      return Double.parseDouble(text);
    } catch (NumberFormatException e) {
      return 0.0;
    }
  }

  /**
   * Cierra la ventana actual.
   *
   * @param actionEvent Evento que dispara el cierre de la ventana.
   */

  @javafx.fxml.FXML
  public void onBtSalir(ActionEvent actionEvent) {

    Node source = (Node) actionEvent.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();

  }

}
