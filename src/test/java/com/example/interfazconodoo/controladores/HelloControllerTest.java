package com.example.interfazconodoo.controladores;


import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextMatchers.hasText;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

  private HelloController controller;

  @Start
  private void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
    Parent root = loader.load();
    controller = loader.getController();

    stage.setScene(new Scene(root));
    stage.show();
  }

  @Test
  void testBotonBuscar(FxRobot robot) {
    robot.clickOn("#textobuscar").write("FormatoA");
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