package studyhub.controlador;


import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import studyhub.business.Comentario;
import studyhub.business.Tema;
import studyhub.data.ComentarioDB;
import studyhub.data.ForoDB;


@WebServlet("/InsertarComentario")
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
        
        ArrayList<Comentario> comentariosEncontrados=ComentarioDB.getComentarios(String.valueOf(idTema));

        StringBuilder htmlBuilder = new StringBuilder();
        for (int i=0; i<asignaturasEncontradas.size(); i++) {
            Comentario comentario =comentariosEncontrados.get(i);
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
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}