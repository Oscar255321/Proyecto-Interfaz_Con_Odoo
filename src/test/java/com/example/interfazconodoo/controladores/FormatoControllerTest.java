package com.example.interfazconodoo.controladores;


import static org.testfx.api.FxAssert.verifyThat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
class FormatoControllerTest {

  private FormatoController controller;

  @Start
  private void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("nuevo_formato.fxml"));
    Parent root = loader.load();
    controller = loader.getController();

    stage.setScene(new Scene(root));
    stage.show();
  }

  @Test
  void testInicializacionFormulario(FxRobot robot) {
    verifyThat("#textoname", TextField::isVisible);
  }

  @Test
  void testValidacionCampos(FxRobot robot) {
    robot.clickOn("#btcrear");
    verifyThat("#btcrear", Button::isDisabled);
  }

  @Test
  void testCrearNuevoFormato(FxRobot robot) {
    robot.clickOn("#textoname").write("NuevoFormato");
    robot.clickOn("#textoformat").write("A4");
    robot.clickOn("#textorientation").write("Portrait");
    robot.clickOn("#margint").write("10");
    robot.clickOn("#marginb").write("10");
    robot.clickOn("#marginl").write("10");
    robot.clickOn("#marginr").write("10");
    robot.clickOn("#btcrear");
  }

  @Test
  void testCerrarVentana(FxRobot robot) {
    robot.clickOn("#btSalir");
  }
}