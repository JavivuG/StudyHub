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
import studyhub.business.Comentario;
import studyhub.business.Tema;
import studyhub.data.ComentarioDB;
import studyhub.data.ForoDB;
import studyhub.data.TemaDB;

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
        
        
        ArrayList<Asignatura> asignaturas=null;
        asignaturas=ForoDB.getAsignaturas(MAX_ASIGNATURAS);
        session.setAttribute("asignaturas", asignaturas);
        
        
        ArrayList<Comentario> comentarios= null;
        comentarios=ComentarioDB.getComents();
        session.setAttribute("comentarios", comentarios);
        
        
        ArrayList<Tema> temas= null;
        temas=TemaDB.getTodosTemas();
        session.setAttribute("temas", temas);
        
        response.sendRedirect("dashboard.jsp");
        
    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}
