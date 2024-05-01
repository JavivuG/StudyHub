package studyhub.controlador;


import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import studyhub.business.Comentario;
import studyhub.business.Tema;
import studyhub.data.ComentarioDB;

@WebServlet(name = "InsertarComentario", urlPatterns = {"/InsertarComentario"}, asyncSupported = true)
public class InsertaComentario extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                HttpSession session = request.getSession();

        String chat = request.getParameter("chat");
        String nickname = request.getRemoteUser();
        int idTema = ((Tema) session.getAttribute("tema")).getId_tema();
        ComentarioDB.setComentario(chat, idTema, nickname);
        
        int idForo = ((Tema) session.getAttribute("tema")).getId_foro();
      /*  String url = "topic.jsp?idForo=" +idForo+ "&idTema="+idTema;
        response.sendRedirect(url);
        */
        
        
        response.setContentType("text/plain; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        ArrayList<Comentario> comentariosEncontrados;
        comentariosEncontrados=ComentarioDB.getComentarios(String.valueOf(idTema));

        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append(" <h2>Comentarios</h2>");
        for (int i=0; i<comentariosEncontrados.size(); i++) {
           Comentario comentario =comentariosEncontrados.get(i);
            htmlBuilder.append(" <div class=\"comentario\">");
            if (request.isUserInRole("moderador") || request.isUserInRole("administrador") || comentario.getNickname().equals(request.getUserPrincipal().getName())){
                htmlBuilder.append("<div class=\"borrar-wrapper\">");
                htmlBuilder.append("<button class=\"borrar-button\" id=\"delete-button\" data-info=\"comentario\" data-url=\"/DeleteComentario?idForo=").append(idForo).append("&idTema=").append(idTema).append("&idComentario=").append(comentario.getId_comentario()).append("&page=topic\">");
                htmlBuilder.append("<i class=\"fa-solid fa-trash-can\"></i>");
                htmlBuilder.append("</button>");
                htmlBuilder.append("</div>");
            }
          
                htmlBuilder.append("<div class=\"datos-comentario\">");
                    htmlBuilder.append("<div class=\"autor-comentario\">");
                        htmlBuilder.append("<img\n" +
"                                        src=\"images/profile.svg\"\n" +
"                                        alt=\"Foto de perfil\"\n" +
"                                        />");
                        htmlBuilder.append("<div class=\"user\">");
                        htmlBuilder.append("<p>").append(comentario.getNickname()).append("</p>");
                        htmlBuilder.append("<p class=\"fecha-comentario\">").append(comentario.getTiempoPublicado()).append("</p>");
                    htmlBuilder.append("</div>");
                htmlBuilder.append("</div>");
            htmlBuilder.append("<p>").append(comentario.getTexto()).append("</p>");
            htmlBuilder.append(" <div class=\"reacciones\">");
                htmlBuilder.append("<div class=\"likes\">");
                    htmlBuilder.append(" <img src=\"images/like.svg\" alt=\"Me gusta\" class=\"like vote\" data-like=\"1\" data-id=\"").append(comentario.getId_comentario()).append("\" />");
                    htmlBuilder.append(" <p id=\"likeCount-").append(comentario.getId_comentario()).append("\">").append(comentario.getLikes()).append(" likes</p>");
                htmlBuilder.append("</div>");
                htmlBuilder.append("<div class=\"dislikes\">");
                    htmlBuilder.append("<img type=\"image\" src=\"images/dislike.svg\" alt=\"No me gusta\" class=\"dislike vote\" data-like=\"0\" data-id=\"").append(comentario.getId_comentario()).append("\" />");
                    htmlBuilder.append("<p id=\"dislikeCount-").append(comentario.getId_comentario()).append("\">").append(comentario.getDislikes()).append(" dislikes</p>");
                htmlBuilder.append("</div>");
            htmlBuilder.append("</div>");
            htmlBuilder.append(" </div>");
            htmlBuilder.append("</div>");
            
            
            htmlBuilder.append(" <script src=\"scripts/confirm_borrar.js\"></script>");


        }

        response.setContentType("text/html");
        response.getWriter().print(htmlBuilder.toString());
    }
}