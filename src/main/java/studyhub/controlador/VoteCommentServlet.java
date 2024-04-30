package studyhub.controlador;

import studyhub.business.VotoComentario;
import studyhub.data.VotoComentarioDB;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/VoteComment")
public class VoteCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idComentario = request.getParameter("idComentario");
        String nickname = request.getRemoteUser();
        int like = Integer.parseInt(request.getParameter("like"));

        VotoComentario voto = new VotoComentario();
        voto.setNickname(nickname);
        voto.setVote(like == 1 ? 1 : -1);
        voto.setId_comentario(Integer.parseInt(idComentario));

        int res = VotoComentarioDB.addVote(voto);

        if (res > 0) {
            int likes = VotoComentarioDB.getLikes(idComentario);
            int dislikes = VotoComentarioDB.getDislikes(idComentario);
            String data = "{\"id\":" + idComentario + ",\"likes\":" + likes + ",\"dislikes\":" + dislikes + "}";
            PrintWriter out = response.getWriter();
            response.setContentType("application/json; charset=UTF-8;");
            out.println(data);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

}