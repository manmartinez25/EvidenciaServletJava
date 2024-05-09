/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package functions;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class EjemploADSO2 {

    public EjemploADSO2() {

    }

    public void insertUsers(String document, String nombre, String especialidad, String ciudad, Connection conexion) {
        try {
            Statement statement = conexion.createStatement();
            statement.executeUpdate("INSERT INTO estudiantes (Documento,Nombre,Especialidad,Ciudad) VALUES ('" + document + "','" + nombre + "','" + especialidad + "','" + ciudad + "')");
            ResultSet rs = statement.executeQuery("SELECT * FROM estudiantes");
            rs.next();
            do {
                System.out.println(rs.getString("Documento") + " : " + rs.getString("Nombre") + " : " + rs.getString("Especialidad") + " : " + rs.getString("Ciudad"));
            } while (rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(EjemploADSO2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String consultUsers(String document, Connection conexion) {
        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM estudiantes WHERE Documento LIKE '%" + document + "%'");
            rs.next();
            return rs.getString("Documento") + " : " + rs.getString("Nombre") + " : " + rs.getString("Especialidad") + " : " + rs.getString("Ciudad");

        } catch (SQLException ex) {
            Logger.getLogger(EjemploADSO2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void deleteUser(String document, Connection conexion) {
        try {
            Statement statement = conexion.createStatement();
            statement.executeUpdate("DELETE FROM estudiantes WHERE Documento LIKE '%" + document + "%'");
            ResultSet rs = statement.executeQuery("SELECT * FROM estudiantes");
            rs.next();
            do {
                System.out.println(rs.getString("Documento") + " : " + rs.getString("Nombre") + " : " + rs.getString("Especialidad") + " : " + rs.getString("Ciudad"));
            } while (rs.next());

        } catch (SQLException ex) {
            Logger.getLogger(EjemploADSO2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateUser(String document, String nombre, String especialidad, String ciudad, Connection conexion) {
        try {
            Statement statement = conexion.createStatement();
            //statement.executeUpdate("UPDATE estudiantes SET (Nombre,Especialidad,Ciudad) = ' " + nombre + " ', ' " + especialidad + " ',' " + ciudad + " ' WHERE Documento = " + document + "");

            String sql = "UPDATE estudiantes SET Nombre = ?, Especialidad = ?, Ciudad = ? WHERE Documento = ?";
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setString(2, especialidad);
                pstmt.setString(3, ciudad);
                pstmt.setString(4, document);
                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Filas actualizadas: " + rowsAffected);
            } catch (SQLException e) {
                System.err.println("Error al ejecutar la consulta SQL: " + e.getMessage());
            }

            ResultSet rs = statement.executeQuery("SELECT * FROM estudiantes");
            rs.next();
            do {
                System.out.println(rs.getString("Documento") + " : " + rs.getString("Nombre") + " : " + rs.getString("Especialidad") + " : " + rs.getString("Ciudad"));
            } while (rs.next());

        } catch (SQLException ex) {
            Logger.getLogger(EjemploADSO2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
