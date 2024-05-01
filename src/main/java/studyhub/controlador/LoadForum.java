/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package studyhub.controlador;

import studyhub.business.Tema;
import studyhub.business.Fichero;
import studyhub.data.TemaDB;
import studyhub.data.FicheroDB;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import studyhub.business.Asignatura;
import studyhub.data.ForoDB;



/**
 *
 * @author javi
 */
@WebServlet(name = "LoadForum", urlPatterns = {"/LoadForum"})
public class LoadForum extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parametros de la peticion
        HttpSession session = request.getSession();
        response.setContentType("text/plain; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        int MAX_ficheroS=4;
        int MAX_TEMAS=3;
        
        ArrayList<Tema> listaTemas, listaTemasDestacados;
        ArrayList<Fichero> listaFicheros;
        String id_foro=request.getParameter("idForo");
        String url="forum.jsp?idForo="+id_foro;
        listaTemas=TemaDB.getTemas(id_foro);

        
        String actualizacion=(String) request.getParameter("actualizacion");
        if (actualizacion!=null){
            StringBuilder htmlBuilder = new StringBuilder();
            for (int i=0; i<listaTemas.size(); i++) {
                Tema tema=listaTemas.get(i);
                htmlBuilder.append("<li class=\"lista-temas-box\">");
                htmlBuilder.append("<a href=\"topic.jsp?idForo=").append(tema.getId_foro()).append("&idTema=").append(tema.getId_tema()).append("\" class=\"enlace-tema\">");
                htmlBuilder.append("<div class=\"caja-tema\">");
                htmlBuilder.append("<h3>").append(tema.getTitulo()).append("</h3>");
                htmlBuilder.append("<p>").append("por <span>").append(tema.getNickname()).append("</span>").append(" • ").append(tema.getTiempoPublicado()).append("</p>");
                htmlBuilder.append("</div>");
                htmlBuilder.append("</a>");

                if (request.isUserInRole("moderador") || request.isUserInRole("administrador")){
                    htmlBuilder.append("<div class=\"borrar-wrapper\">");
                    htmlBuilder.append("<button class=\"borrar-button\" id=\"delete-button\" data-info=\"topic\" data-url=\"/DeleteTopic?idForo=").append(tema.getId_foro()).append("&idTema=").append(tema.getId_tema()).append("&page=forum\">");
                    htmlBuilder.append("<i class=\"fa-solid fa-trash-can\"></i>");
                    htmlBuilder.append("</button>");
                    htmlBuilder.append("</div>");
                }

                htmlBuilder.append("</li>");
            }

            response.setContentType("text/html");
            response.getWriter().print(htmlBuilder.toString());
        }
        else {
            listaTemasDestacados=TemaDB.getTemasDestacados(id_foro,MAX_TEMAS);
            listaFicheros=FicheroDB.getFicherosRecientes(id_foro,MAX_ficheroS);
            Asignatura asignatura=ForoDB.getAsignatura(id_foro);

            session.setAttribute("asignatura", asignatura);
            session.setAttribute("temas", listaTemas);
            session.setAttribute("temas_destacados", listaTemasDestacados);
            session.setAttribute("ficheros", listaFicheros);

            response.sendRedirect(url);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}
