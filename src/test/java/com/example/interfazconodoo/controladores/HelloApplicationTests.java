package com.example.interfazconodoo;


import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextMatchers.hasText;

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
 * Author: Usuario Date: 12/02/2025 Description:
 */
@ExtendWith(ApplicationExtension.class)
class HelloApplicationTests {

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

  /**
   * En este test lo que hacemos es realizar la busqueda de un elemento en la inteerfaz.
   * @param robot
   */

  @DisplayName("Realizamos la busqueda de un elemento")

  @Test
  void testBotonBuscar(FxRobot robot) {
    robot.clickOn("#textobuscar").write("Carta");
    robot.clickOn("#btBuscar");
    robot.sleep(2000);
    verifyThat("#tbDatos", tableView -> !tableView.getId().isEmpty());
  }

  /**
   * En este test lo que hacemos es realizar la creacion de un elemento en la inteerfaz.
   * @param robot
   */

  @DisplayName("Realizamos la cracion de un elemento")

  @Test
  void testBotonCrearFormato(FxRobot robot) {

    robot.clickOn("#btCrear");
    robot.sleep(1000);
    FxAssert.verifyThat(robot.window("Crear Formato"), WindowMatchers.isShowing());
    robot.targetWindow("Crear Formato");
    //FxAssert.verifyThat("#tfTitulo", LabeledMatchers.hasText("Nuevo formato"));
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

  /**
   * En este test lo que hacemos es realizar la edicion de un elemento en la inteerfaz.
   * @param robot
   */

  @DisplayName("Realizamos la edicion de un elemento")

  @Test
  void testBotonEditarFormato(FxRobot robot) {

    //Buscamos el formato que queremos modificar.
    robot.clickOn("#textobuscar").write("Carta");
    robot.clickOn("#btBuscar");
    robot.sleep(2000);
    verifyThat("#tbDatos", tableView -> !tableView.getId().isEmpty());

    //Lo seleccionamos en la tabla
    robot.clickOn("#tbDatos .table-row-cell");
    robot.clickOn("#btEditar");
    //Se abre la nueva ventana
    FxAssert.verifyThat(robot.window("Modificar Formato"), WindowMatchers.isShowing());
    robot.targetWindow("Modificar Formato");
    robot.sleep(1000);

    //Introducimos los datos modificados
    robot.doubleClickOn("#textoname").eraseText(1).write("Carta_Editada");
    robot.sleep(500);
    robot.doubleClickOn("#textoformat").eraseText(1).write("A3");
    robot.sleep(500);
    robot.doubleClickOn("#textorientation").eraseText(1).write("Left");
    robot.sleep(500);
    robot.doubleClickOn("#margint").eraseText(1).write("15");
    robot.sleep(500);
    robot.doubleClickOn("#marginb").eraseText(1).write("15");
    robot.sleep(500);
    robot.doubleClickOn("#marginl").eraseText(1).write("8");
    robot.sleep(500);
    robot.doubleClickOn("#marginr").eraseText(1).write("8");
    robot.sleep(500);
    robot.clickOn("#btcrear");
    robot.sleep(1000);
    robot.targetWindow("Confirmación");
    robot.sleep(1000);
    robot.moveTo("Aceptar");
    robot.clickOn();
    robot.sleep(500);

  }

  /**
   * En este test lo que hacemos es realizar la eliminacion de un elemento en la inteerfaz.
   * @param robot
   */

  @DisplayName("Realizamos la eliminacion de un elemento")

  @Test
  void testBotonEliminarFormato(FxRobot robot) {

    //Buscamos el formato que queremos modificar.
    robot.clickOn("#textobuscar").write("CartaNueva");
    robot.clickOn("#btBuscar");
    robot.sleep(2000);
    verifyThat("#tbDatos", tableView -> !tableView.getId().isEmpty());

    robot.clickOn("#tbDatos .table-row-cell");
    robot.clickOn("#btEliminar");
    robot.targetWindow("ADVERTENCIA!");
    robot.sleep(1000);
    robot.moveTo("Aceptar");
    robot.clickOn();
    robot.sleep(2000);

  }

  /**
   * En este test lo que hacemos es verificar que se cierra la ventana en la inteerfaz.
   * @param robot
   */

  @DisplayName("Cerramos la ventana secundaria")

  @Test
  void testBotonCerrarVentana(FxRobot robot){

    robot.clickOn("#btCrear");
    robot.sleep(1000);
    FxAssert.verifyThat(robot.window("Crear Formato"), WindowMatchers.isShowing());
    robot.targetWindow("Crear Formato");
    robot.sleep(1000);
    robot.clickOn("#btsalir");
    robot.sleep(1000);

  }
}