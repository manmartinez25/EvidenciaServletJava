/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import functions.EjemploADSO2;

/**
 *
 * @author USER
 */
public class ServletUsuarios extends HttpServlet {

    EjemploADSO2 metodosCrud = new EjemploADSO2();
    Connection conexion = null;

    public ServletUsuarios() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }

        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/ADSO";

        try {
            conexion = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(ServletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String msg)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletUsuarios</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>"+ msg + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    @SuppressWarnings("empty-statement")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String document = request.getParameter("documento");
        String name = request.getParameter("nombre");
        String espec = request.getParameter("especialidad");
        String city = request.getParameter("ciudad");
        String tipoSolicitud = request.getParameter("submit");
        
        String resultado = "Fallido";
        
        if (tipoSolicitud != null) {
            
            if (tipoSolicitud.equals("Insertar")) {
                metodosCrud.insertUsers(document, name, espec, city, conexion);
                resultado = "Registro exitoso, datos guardados del usuario: "+document+" "+name+" "+espec+" "+city;
            }
            if (tipoSolicitud.equals("Consultar")) {
                String resultConsulta = metodosCrud.consultUsers(document, conexion);
                resultado = "Consulta exitosa, datos del usuario: "+resultConsulta;
            }
            if (tipoSolicitud.equals("Borrar")) {
                metodosCrud.deleteUser(document, conexion);
                resultado = "Eliminación de usuario exitosa";
            }
            if (tipoSolicitud.equals("Actualizar")) {
                metodosCrud.updateUser(document, name, espec, city, conexion);
                resultado = "Actualización exitosa, nuevos datos del usuario: "+document+" "+name+" "+espec+" "+city;
            }
            processRequest(request, response, resultado);
        }
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
