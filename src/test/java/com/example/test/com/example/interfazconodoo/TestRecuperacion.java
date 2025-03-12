package com.example.interfazconodoo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.WindowMatchers;

/**
 * Author: Usuario
 * Date: 12/03/2025
 * Description: 
 */
@ExtendWith(ApplicationExtension.class)
class TestRecuperacion {

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

  @DisplayName("Realizamos la búsqueda de un elemento")
  @Test
  void testBotonBuscar(FxRobot robot) {
    robot.clickOn("#textobuscar").write("Carta");
    robot.clickOn("#btBuscar");
    robot.sleep(2000);
    FxAssert.verifyThat("#tbDatos", tableView -> tableView.isVisible() && !tableView.getId().isEmpty());
  }

  @DisplayName("Realizamos la creación de un elemento")
  @Test
  void testBotonCrearFormato(FxRobot robot) {
    robot.clickOn("#btCrear");
    robot.sleep(1000);
    FxAssert.verifyThat(robot.window("Crear Formato"), WindowMatchers.isShowing());
    robot.targetWindow("Crear Formato");
    robot.sleep(1000);

    robot.clickOn("#textoname").write("CartaNueva");
    robot.clickOn("#textoformat").write("A4");
    robot.clickOn("#textorientation").write("Portrait");
    robot.clickOn("#margint").write("10");
    robot.clickOn("#marginb").write("10");
    robot.clickOn("#marginl").write("10");
    robot.clickOn("#marginr").write("10");

    robot.clickOn("#btcrear");
    robot.sleep(1000);
    robot.closeCurrentWindow();
  }

  @DisplayName("Realizamos la edición de un elemento")
  @Test
  void testBotonEditarFormato(FxRobot robot) {
    robot.clickOn("#textobuscar").write("Carta");
    robot.clickOn("#btBuscar");
    robot.sleep(2000);
    FxAssert.verifyThat("#tbDatos", tableView -> tableView.isVisible() && !tableView.getId().isEmpty());

    robot.clickOn("#tbDatos .table-row-cell");
    robot.clickOn("#btEditar");
    FxAssert.verifyThat(robot.window("Modificar Formato"), WindowMatchers.isShowing());
    robot.targetWindow("Modificar Formato");
    robot.sleep(1000);

    robot.doubleClickOn("#textoname").eraseText(10).write("Carta_Editada");
    robot.doubleClickOn("#textoformat").eraseText(10).write("A3");
    robot.doubleClickOn("#textorientation").eraseText(10).write("Left");
    robot.doubleClickOn("#margint").eraseText(2).write("15");
    robot.doubleClickOn("#marginb").eraseText(2).write("15");
    robot.doubleClickOn("#marginl").eraseText(2).write("8");
    robot.doubleClickOn("#marginr").eraseText(2).write("8");

    robot.clickOn("#btcrear");
    robot.sleep(1000);
    robot.targetWindow("Confirmación");
    robot.sleep(1000);
    robot.moveTo("Aceptar").clickOn();
    robot.sleep(500);
  }

  @DisplayName("Realizamos la eliminación de un elemento")
  @Test
  void testBotonEliminarFormato(FxRobot robot) {
    robot.clickOn("#textobuscar").write("CartaNueva");
    robot.clickOn("#btBuscar");
    robot.sleep(2000);
    FxAssert.verifyThat("#tbDatos", tableView -> tableView.isVisible() && !tableView.getId().isEmpty());

    robot.clickOn("#tbDatos .table-row-cell");
    robot.clickOn("#btEliminar");
    FxAssert.verifyThat(robot.window("ADVERTENCIA!"), WindowMatchers.isShowing());
    robot.sleep(1000);
    robot.moveTo("Aceptar").clickOn();
    robot.sleep(2000);
    FxAssert.verifyThat("#tbDatos", );
  }

  @DisplayName("Cerramos la ventana secundaria")
  @Test
  void testBotonCerrarVentana(FxRobot robot) {
    robot.clickOn("#btCrear");
    robot.sleep(1000);
    FxAssert.verifyThat(robot.window("Crear Formato"), WindowMatchers.isShowing());
    robot.targetWindow("Crear Formato");
    robot.sleep(1000);
    robot.clickOn("#btsalir");
    robot.sleep(1000);
  }
}