/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package studyhub.controlador;

import studyhub.business.Asignatura;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import studyhub.data.ForoDB;
import studyhub.data.UserDB;

/**
 *
 * @author javi
 */
@WebServlet(name = "LoadDashboard", urlPatterns = {"/LoadDashboard"})
public class LoadDashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parametros de la peticion
        HttpSession session = request.getSession();
        int MAX_ASIGNATURAS=9;
        
        UserDB.inicioSesion(request.getRemoteUser());
        ArrayList<Asignatura> asignaturas=null;
        asignaturas=ForoDB.getAsignaturas(MAX_ASIGNATURAS);
        
        
        // Almacena los datos en el alcance de la solicitud
        session.setAttribute("asignaturas", asignaturas);
        response.sendRedirect("dashboard.jsp");
    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}
