package com.example.interfazconodoo;


import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextMatchers.hasText;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/**
 * Author: Usuario Date: 12/02/2025 Description:
 */
@ExtendWith(ApplicationExtension.class)
class HelloControllerTest {

  Pane mainroot;
  Stage mainstage;

  @Start
  private void start(Stage stage) throws Exception {
    mainroot = (Pane) FXMLLoader.load(getClass().getResource("hello-view.fxml"));
    mainstage = stage;
    stage.setScene(new Scene(mainroot));
    stage.show();
    stage.toFront();
  }

  @Test
  void testBotonBuscar(FxRobot robot) {
    robot.clickOn("#textobuscar").write("A4");
    robot.sleep(1000);
    robot.clickOn("#btBuscar");
    verifyThat("#tbDatos", tableView -> !tableView.getId().isEmpty());
  }

  @Test
  void testBotonCrearFormato(FxRobot robot) {
    robot.clickOn("#btCrear");
    verifyThat(".title", hasText("Crear Formato"));
  }

  @Test
  void testBotonEditarFormato(FxRobot robot) {
    robot.clickOn("#tbDatos");
    robot.clickOn("#btEditar");
    verifyThat(".title", hasText("Modificar Formato"));
  }

  @Test
  void testBotonEliminarFormato(FxRobot robot) {
    robot.clickOn("#tbDatos");
    robot.clickOn("#btEliminar");
    verifyThat(".dialog-pane", hasText("¿Estas seguro que quieres eliminar el formato?"));
  }
}