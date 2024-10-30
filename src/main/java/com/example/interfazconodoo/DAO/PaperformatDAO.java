package com.example.interfazconodoo.DAO;

import com.example.interfazconodoo.Modelos.Paperformat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaperformatDAO {

    public static List<Paperformat> obtenerFormatos() throws SQLException {
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
    }






}
