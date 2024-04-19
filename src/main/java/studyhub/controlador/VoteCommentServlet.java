package studyhub.controlador;

import studyhub.business.Asignatura;
import studyhub.business.Comentario;
import studyhub.business.Tema;
import studyhub.business.VotoComentario;
import studyhub.data.VotoComentarioDB;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/VoteComment")
public class VoteCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String idComentario = request.getParameter("idComentario");
        String nickname = request.getRemoteUser();
        int like = Integer.parseInt(request.getParameter("like"));

        String url = "";

        VotoComentario voto = new VotoComentario();
        voto.setNickname(nickname);
        voto.setVote(like == 1 ? 1 : -1);
        voto.setId_comentario(Integer.parseInt(idComentario));

        int res = VotoComentarioDB.addVote(voto);

        if (res > 0) {
            Tema tema = (Tema) session.getAttribute("tema");
            Asignatura asignatura = (Asignatura) session.getAttribute("asignatura");
            ArrayList<Comentario> listaComentarios = (ArrayList<Comentario>) session.getAttribute("comentarios");
            session.setAttribute("tema", tema);
            session.setAttribute("asignatura", asignatura);
            session.setAttribute("comentarios", listaComentarios);
            url = "topic.jsp?idForo=" + tema.getId_foro() + "&idTema=" + tema.getId_tema();
        } else {
            // Error al registrar usuario
            url = "signup.jsp";
        }

        response.sendRedirect(url);

    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

}