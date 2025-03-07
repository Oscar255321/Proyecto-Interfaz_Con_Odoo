package com.example.interfazconodoo;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal que inicia la aplicación JavaFX.
 *
 * <p>Esta clase extiende de {@link Application} y se encarga de cargar la interfaz gráfica
 * definida en un archivo FXML, aplicar estilos CSS y mostrar la ventana principal.</p>
 *
 * @author Oscar Abellan
 * @version 1.0
 */

public class HelloApplication extends Application {

  /**
   * Método sobrescrito de {@link Application} que se ejecuta al iniciar la aplicación.
   *
   * <p>Carga la interfaz gráfica desde un archivo FXML, aplica una hoja de estilos CSS,
   * configura el título de la ventana y la muestra en pantalla.</p>
   *
   * @param stage La ventana principal de la aplicación.
   * @throws IOException Si ocurre un error al cargar el archivo FXML.
   */

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 750, 560);
    scene.getStylesheets().add(getClass().getResource("estilos.css").toExternalForm());
    stage.setTitle("Paperformat Connection");
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Método principal que inicia la ejecución de la aplicación.
   *
   * <p>Llama al método {@code launch()} de {@link Application}, el cual gestiona el ciclo
   * de vida de la aplicación JavaFX.</p>
   *
   * @param args Argumentos de línea de comandos (no utilizados en esta aplicación).
   */

  public static void main(String[] args) {
    launch();
  }
}