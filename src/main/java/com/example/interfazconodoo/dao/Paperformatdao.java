package com.example.interfazconodoo.dao;


import com.example.interfazconodoo.modelos.Paperformat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 * Clase para gestionar la comunicación con la base de datos PostgreSQL.
 *
 * <p>Proporciona métodos para realizar operaciones CRUD en la tabla "report_paperformat".</p>
 *
 * @author Oscar Abellan
 * @version 1.1
 */

public class Paperformatdao {

  /**
   * Obtiene los formatos almacenados en la base de datos que coincidan con el nombre proporcionado.
   *
   * @param nombre Nombre parcial o completo del formato a buscar.
   * @return Lista de objetos {@link Paperformat} que coinciden con el criterio de búsqueda.
   * @throws SQLException Si ocurre un error al ejecutar la consulta.
   */

  public static List<Paperformat> obtenerFormatos(String nombre) throws SQLException {
    List<Paperformat> formatos = new ArrayList<>();

    try (Connection conexion = Conexion.getConnection();
        PreparedStatement statement = conexion.prepareStatement(
            "SELECT * FROM report_paperformat WHERE name like ?");) {

      statement.setString(1, "%" + nombre + "%");
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        Paperformat formato = new Paperformat();
        formato.setId(resultSet.getInt("id"));
        formato.setName(resultSet.getString("name"));
        formato.setFormat(resultSet.getString("format"));
        formato.setOrientation(resultSet.getString("orientation"));
        formato.setMarginTop(resultSet.getDouble("margin_top"));
        formato.setMarginBottom(resultSet.getDouble("margin_bottom"));
        formato.setMarginLeft(resultSet.getDouble("margin_left"));
        formato.setMarginRight(resultSet.getDouble("margin_right"));
        formatos.add(formato);
      }
    }

    return formatos;
  }

  /**
   * Inserta un nuevo formato en la base de datos.
   *
   * @param formato Objeto {@link Paperformat} a insertar.
   * @throws SQLException Si ocurre un error durante la inserción.
   */

  public static void crearFormato(Paperformat formato) throws SQLException {

    try (Connection conexion = Conexion.getConnection();
        PreparedStatement statement = conexion.prepareStatement(
            "INSERT INTO report_paperformat (name, format, orientation, margin_top, margin_bottom"
                + ",margin_right, margin_left) " + "VALUES (?, ?, ?, ?, ?, ?, ?)")) {

      statement.setString(1, formato.getName());
      statement.setString(2, formato.getFormat());
      statement.setString(3, formato.getOrientation());
      statement.setDouble(4, formato.getMarginTop());
      statement.setDouble(5, formato.getMarginBottom());
      statement.setDouble(6, formato.getMarginLeft());
      statement.setDouble(7, formato.getMarginRight());
      statement.executeUpdate();

      // Mostrar un mensaje de éxito
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Información");
      alert.setHeaderText("El formato ha sido creado correctamente.");
      alert.showAndWait();
    }

  }

  /**
   * Elimina un formato de la base de datos según su ID.
   *
   * @param formato Objeto {@link Paperformat} que se eliminará.
   * @throws SQLException Si ocurre un error durante la eliminación.
   */

  public static void eliminarFormato(Paperformat formato) throws SQLException {

    try (Connection conexion = Conexion.getConnection();
        PreparedStatement statement = conexion.prepareStatement("DELETE FROM report_paperformat "
            + "WHERE name = ?");) {

      statement.setString(1, formato.getName());
      statement.executeUpdate();

    }

  }

  /**
   * Actualiza un formato existente en la base de datos.
   *
   * @param formato Objeto {@link Paperformat} con los datos actualizados.
   * @throws SQLException Si ocurre un error durante la actualización.
   */

  public static void editarFormato(Paperformat formato) throws SQLException {

    if (formato.getId() == 0) {
      System.err.println("El ID del formato es null, no se puede editar sin un ID.");
      return;
    }

    try (Connection conexion = Conexion.getConnection();
        PreparedStatement statement = conexion.prepareStatement(
            "UPDATE report_paperformat SET name = ?, format = ?, orientation = ?, margin_top = ?, "
                +
                "margin_bottom = ?, margin_left = ?, margin_right = ? WHERE id = ?")) {

      statement.setString(1, formato.getName());
      statement.setString(2, formato.getFormat());
      statement.setString(3, formato.getOrientation());
      statement.setDouble(4, formato.getMarginTop());
      statement.setDouble(5, formato.getMarginBottom());
      statement.setDouble(6, formato.getMarginLeft());
      statement.setDouble(7, formato.getMarginRight());
      statement.setInt(8, formato.getId());
      statement.executeUpdate();

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Confirmación");
      alert.setHeaderText(null);
      alert.setContentText("El formato ha sido editado correctamente.");
      alert.showAndWait();

    } catch (SQLException e) {
      System.err.println("Error al editar el formato");
      e.printStackTrace();
    }
  }
}

