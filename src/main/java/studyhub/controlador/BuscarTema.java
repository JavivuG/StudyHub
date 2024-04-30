package studyhub.controlador;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import studyhub.business.Tema;
import studyhub.data.TemaDB;

@WebServlet(name = "BuscarTema", urlPatterns = {"/BuscarTema"}, asyncSupported = true)
public class BuscarTema extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String busqueda=(String) request.getParameter("q");
        ArrayList<Tema> temasEncontrados=TemaDB.buscarTema(busqueda);

        StringBuilder htmlBuilder = new StringBuilder();
        for (int i=0; i<temasEncontrados.size(); i++) {
            Tema tema=temasEncontrados.get(i);
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
}
