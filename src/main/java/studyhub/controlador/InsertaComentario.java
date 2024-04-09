package studyhub.controlador;

import studyhub.business.User;
import studyhub.data.UserDB;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import studyhub.business.Asignatura;
import studyhub.business.Tema;
import studyhub.data.ComentarioDB;


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
        String url = "topic.jsp?idForo=" +idForo+ "&idTema="+idTema;
        response.sendRedirect(url);
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}