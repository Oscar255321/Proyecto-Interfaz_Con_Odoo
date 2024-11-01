package com.example.interfazconodoo.DAO;

import com.example.interfazconodoo.Modelos.Paperformat;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaperformatDAO {

    /*public static List<Paperformat> obtenerFormatos() throws SQLException {
        List<Paperformat> formatos = new ArrayList<>();

        try (Connection conexion = ConexionDB.getConnection();
             Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM report_paperformat ORDER BY name ")) {

            while (resultSet.next()) {
                Paperformat formato = new Paperformat();
                formato.setName(resultSet.getString("name"));
                formato.setFormat(resultSet.getString("format"));
                formato.setOrientation(resultSet.getString("orientation"));
                formato.setMargin_top(resultSet.getDouble("margin_top"));
                formato.setMargin_bottom(resultSet.getDouble("margin_bottom"));
                formato.setMargin_left(resultSet.getDouble("margin_left"));
                formato.setMargin_right(resultSet.getDouble("margin_right"));
                formatos.add(formato);
            }
        }

        return formatos;
    }*/

    public static List<Paperformat> obtenerFormatos(String Nombre) throws SQLException {
        List<Paperformat> formatos = new ArrayList<>();

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement statement = conexion.prepareStatement("SELECT * FROM report_paperformat WHERE name like ?");) {

            statement.setString(1, "%" + Nombre + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Paperformat formato = new Paperformat();
                formato.setName(resultSet.getString("name"));
                formato.setFormat(resultSet.getString("format"));
                formato.setOrientation(resultSet.getString("orientation"));
                formato.setMargin_top(resultSet.getDouble("margin_top"));
                formato.setMargin_bottom(resultSet.getDouble("margin_bottom"));
                formato.setMargin_left(resultSet.getDouble("margin_left"));
                formato.setMargin_right(resultSet.getDouble("margin_right"));
                formatos.add(formato);
            }
        }

        return formatos;
    }

    public static void crearFormato (Paperformat formato) throws SQLException {

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement statement = conexion.prepareStatement
                     ("INSERT INTO report_paperformat (name, format, orientation, margin_top, margin_bottom, margin_right, margin_left) " +
                             "VALUES (?,?,?,?,?,?,?)");) {

            statement.setString(1, formato.getName());
            statement.setString(2, formato.getFormat());
            statement.setString(3, formato.getFormat());
            statement.setDouble(4, formato.getMargin_top());
            statement.setDouble(5, formato.getMargin_bottom());
            statement.setDouble(6, formato.getMargin_left());
            statement.setDouble(7, formato.getMargin_right());
            statement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("El formato a sido creado correctamente.");
            alert.showAndWait();
        }

    }

    public static void eliminarFormato (Paperformat formato) throws SQLException {

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement statement = conexion.prepareStatement
                     ("DELETE FROM report_paperformat WHERE name = ?");) {

            statement.setString(1, formato.getName());
            statement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("El formato a sido eliminado correctamente.");
            alert.showAndWait();
        }

    }

    public static void editarFormato (Paperformat formato) throws SQLException {

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement statement = conexion.prepareStatement
                     ("UPDATE report_paperformat SET name = ?, format = ?  id = ?");) {

            statement.setString(1, formato.getName());
            statement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("El formato a sido eliminado correctamente.");
            alert.showAndWait();
        }

    }
}
