package com.example.interfazconodoo;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase para iniciar el programa.
 *
 * @author Oscar Abellan
 * @version 1.0
 */

public class HelloApplication extends Application {

  /**
   * Creamos la interfaz.
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
   * Inicia la aplicación.
   */

  public static void main(String[] args) {
    launch();
  }
}