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

/**
 *
 * @author javi
 */
@WebServlet(name = "LoadSubjects", urlPatterns = {"/LoadSubjects"})
public class LoadSubjects extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parametros de la peticion
        HttpSession session = request.getSession();
        ArrayList<Asignatura> asignaturas=null;
        asignaturas=ForoDB.getAsignaturas();

        String actualizacion=(String) request.getParameter("actualizacion");
        if (actualizacion!=null){
            StringBuilder htmlBuilder = new StringBuilder();
            for (int i=0; i<asignaturas.size(); i++) {
                Asignatura asignatura=asignaturas.get(i);
                htmlBuilder.append("<div class=\"ag-courses_item\">");
                htmlBuilder.append("<a href=\"forum.jsp?idForo=").append(asignatura.getID_asignatura()).append("\" class=\"ag-courses-item_link\">");
                htmlBuilder.append("<div class=\"ag-courses-item_bg\"></div>");
                htmlBuilder.append("<div class=\"ag-courses-item_title\">").append(asignatura.getNombre()).append("</div>");
                htmlBuilder.append("<div class=\"ag-courses-item_description-box\">");
                htmlBuilder.append("<span class=\"ag-courses-item_description\">").append(asignatura.getCurso()).append("</span>");
                htmlBuilder.append("</div>");
                htmlBuilder.append("</a>");
                htmlBuilder.append("</div>");
            }

            response.setContentType("text/html");
            response.getWriter().print(htmlBuilder.toString());
        }
        else {
            // Almacena los datos en el alcance de la solicitud
            session.setAttribute("asignaturas", asignaturas);
            response.sendRedirect("subjects.jsp");
        }
        
    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}
