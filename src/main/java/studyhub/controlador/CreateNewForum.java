package studyhub.controlador;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import studyhub.data.ForoDB;

@WebServlet(name = "CreateNewForum", urlPatterns = {"/CreateNewForum"}, asyncSupported = true)
public class CreateNewForum extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");


        if (request.isUserInRole("administrador") || request.isUserInRole("moderador")){
           String asignatura = (String) request.getParameter("asignatura");
            int curso = Integer.parseInt(request.getParameter("curso"));
            String grado = (String) request.getParameter("grado");
        
            boolean asignaturaExists = ForoDB.asignaturaExists(asignatura);
            String cursoGrado=curso+"º Curso de "+grado;
            if (!asignaturaExists){
                ForoDB.crearAsignatura(asignatura, cursoGrado);
            }
            else {
                response.sendRedirect("error.jsp");
            }
        }
        else {
            response.sendRedirect("error.jsp");
        }
        
        response.sendRedirect("subjects.jsp");

        
    }
}
