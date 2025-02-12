package com.example.interfazconodoo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexión con la base de datos PostgreSQL.
 *
 * @author Oscar Abellan
 * @version 1.0
 */

public class Conexion {

  /**
   * Instancia única de la conexión a la base de datos.
   */

  public static Connection connection = null;

  /**
   * Obtiene la conexión a la base de datos.
   *
   * <p>Si la conexión aún no ha sido creada, este método la inicializa. En caso
   * de fallo, lanza una excepción en tiempo de ejecución.</p>
   *
   * @return La conexión activa a la base de datos.
   * @throws RuntimeException Si ocurre un error al intentar conectar a la base de datos.
   */

  public static Connection getConnection() {

    String dbName = "Empresa_db";
    String dbPort = "5432";
    String username = "odoo";
    String password = "odoo";

    try {
      Class.forName("org.postgresql.Driver");
      String url = "jdbc:postgresql://localhost:" + dbPort + "/" + dbName + "?user="
          + username + "&password=" + password;
      connection = DriverManager.getConnection(url, username, password);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      System.exit(-1);
    }
    return connection;
  }
}

