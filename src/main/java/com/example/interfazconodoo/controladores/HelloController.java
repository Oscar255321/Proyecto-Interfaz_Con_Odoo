
package com.example.interfazconodoo.controladores;

import com.example.interfazconodoo.HelloApplication;
import com.example.interfazconodoo.dao.Informedao;
import com.example.interfazconodoo.dao.Paperformatdao;
import com.example.interfazconodoo.modelos.Paperformat;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

/**
 * Controlador para gestionar la interfaz de visualización, búsqueda,
 * edición y eliminación de formatos de papel.
 *
 * <p>Esta clase gestiona la tabla de datos y permite realizar
 * operaciones como la búsqueda de formatos,
 * edición de registros existentes, eliminación y creación de nuevos formatos.</p>
 *
 * @author Oscar Abellan
 * @version 1.1
 */

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
  private Button btBuscar;
  @FXML
  private Button btCrear;
  @FXML
  private Button btinforme;
  @FXML
  private TextField textobuscar;


  private ObservableList<Paperformat> datos = FXCollections.observableArrayList();



  /**
   * Inicializa la tabla de datos.
   *
   * <p>Binding reactivo para desactivar btEliminar y btEditar si no hay selección.</p>
   *
   * <p>Detectar cambios en el campo de búsqueda y aplicar el filtro reactivo.</p>
   */


  public void initialize() {

    flName.setCellValueFactory(new PropertyValueFactory<>("name"));
    flFormat.setCellValueFactory(new PropertyValueFactory<>("format"));
    flOrientation.setCellValueFactory(new PropertyValueFactory<>("orientation"));
    flMarginTop.setCellValueFactory(new PropertyValueFactory<>("marginTop"));
    flMarginBotton.setCellValueFactory(new PropertyValueFactory<>("marginBottom"));
    flMarginRight.setCellValueFactory(new PropertyValueFactory<>("marginRight"));
    flMarginLeft.setCellValueFactory(new PropertyValueFactory<>("marginLeft"));

    tbDatos.setItems(datos);

    BooleanBinding noSelection = tbDatos.getSelectionModel().selectedItemProperty().isNull();
    btEditar.disableProperty().bind(noSelection);
    btEliminar.disableProperty().bind(noSelection);

    textobuscar.textProperty().addListener((obs, oldText, newText) -> {
      if (newText.isEmpty()) {
        datos.clear(); // Limpiar la lista si el campo de búsqueda está vacío
      }
    });
  }

  /**
   * Realiza una búsqueda de formatos por nombre y actualiza la tabla con los resultados.
   *
   * @param actionEvent Evento que dispara la búsqueda.
   */

  @FXML
  public void onbBtnSearchBien(ActionEvent actionEvent) {
    Task<Void> tarea = new Task<Void>() {
      @Override
      protected Void call() throws Exception {
        try {
          List<Paperformat> formatos = Paperformatdao.obtenerFormatos(textobuscar.getText());
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

  /**
   * Abre la ventana de edición de un formato seleccionado.
   *
   * @param actionEvent Evento que dispara la acción.
   * @throws IOException Si hay un error al cargar la interfaz.
   */

  @FXML
  public void onbBtnEditarFormat(ActionEvent actionEvent) throws IOException {

    Paperformat formatoInicial = tbDatos.getSelectionModel().getSelectedItem();

    if (formatoInicial != null) {
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(
            HelloApplication.class.getResource("nuevo_formato.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        scene.getStylesheets()
            .add(HelloApplication.class.getResource("estilos.css").toExternalForm());

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

  /**
   * Elimina el formato seleccionado tras confirmar la acción con una alerta.
   *
   * @param actionEvent Evento que dispara la acción.
   * @throws IOException SQLException Si ocurre un error durante la eliminación.
   */

  @FXML
  public void onbBtnEliminarFormat(ActionEvent actionEvent) throws IOException, SQLException {

    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("ADVERTENCIA!");
    alert.setHeaderText(null);
    alert.setContentText("¿Estas seguro que quieres eliminar el formato?");
    alert.showAndWait();

    Paperformat borrar = tbDatos.getSelectionModel().getSelectedItem();

    if (alert.getResult() == ButtonType.OK) {
      try {
        Paperformatdao.eliminarFormato(borrar);
        onbBtnSearchBien(null); // Refrescar la tabla después de eliminar
      } catch (SQLException e) {
        System.err.println("Error al eliminar: " + e.getMessage());
      }
    }
  }

  /**
   * Abre la ventana para crear un nuevo formato.
   *
   * @param actionEvent Evento que dispara la acción.
   * @throws IOException Si hay un error al cargar la interfaz.
   */

  @FXML
  public void onbBtnCrearFormat(ActionEvent actionEvent) throws IOException {

    FXMLLoader fxmlLoader = new FXMLLoader(
        HelloApplication.class.getResource("nuevo_formato.fxml"));
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

  @FXML
  public void onImprimirInformeClick(ActionEvent actionEvent) {

    try {
      new Informedao().mostrarInforme();
    } catch (ClassNotFoundException | JRException | SQLException e1) {
      e1.printStackTrace();
    }
  }


}
