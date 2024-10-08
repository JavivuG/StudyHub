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
        String url;


        if (request.isUserInRole("administrador") || request.isUserInRole("moderador")){
           String asignatura = (String) request.getParameter("asignatura");
            int curso = Integer.parseInt(request.getParameter("curso"));
            String grado = (String) request.getParameter("grado");
        
            boolean asignaturaExists = ForoDB.asignaturaExists(asignatura);
            String cursoGrado=curso+"º Curso de "+grado;
            if (!asignaturaExists){
                if (asignatura.length()<=100 && cursoGrado.length()<=100){
                    ForoDB.crearAsignatura(asignatura, cursoGrado);
                    url="subjects.jsp";
                }
                else {
                    url="error.jsp";
                }
            }
            else {
                url="error.jsp";
            }
        }
        else {
            url="not_found.jsp";
        }
        
        response.sendRedirect(url);

        
    }
}
