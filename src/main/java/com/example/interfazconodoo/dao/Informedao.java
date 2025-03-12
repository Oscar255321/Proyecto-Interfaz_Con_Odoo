package com.example.interfazconodoo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

/**
 * Author: Usuario Date: 12/03/2025 Description: Creacion de informes
 */
public class Informedao extends JFrame {

  private static final long serialVersionUID = 1L;

  public void mostrarFormulario() throws JRException, ClassNotFoundException, SQLException {

    Connection conexion = Conexion.getConnection();

    String reportSrcFile = "";

    JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

    HashMap<String, Object> parameters = new HashMap<String, Object>();

    JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conexion);
    JRViewer viewer = new JRViewer(print);
    viewer.setOpaque(true);
    viewer.setVisible(true);
    this.add(viewer);
    this.setSize(800, 600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    System.out.println("Informe generado correctamente!");
  }

  public static void main(String[] args) {
    try {
      new Informedao().mostrarFormulario();
    } catch (JRException | ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
}

}
