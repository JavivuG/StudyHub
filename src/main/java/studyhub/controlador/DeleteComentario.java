/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package studyhub.controlador;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studyhub.business.Tema;
import studyhub.data.ComentarioDB;
import studyhub.data.TemaDB;
import studyhub.data.UserDB;



/**
 *
 * @author javi
 */
@WebServlet(name = "DeleteComentario", urlPatterns = {"/DeleteComentario"})
public class DeleteComentario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parametros de la peticion
        String id_tema=request.getParameter("idTema");
        Tema tema=TemaDB.getTema(id_tema);
        String url="topic.jsp?idForo="+tema.getId_foro()+"&idTema="+id_tema;

        int id_comentario=Integer.parseInt(request.getParameter("idComentario"));

        if (request.isUserInRole("administrador") || request.isUserInRole("moderador") || ComentarioDB.isOwner(id_comentario, UserDB.selectUser(request.getRemoteUser()).getNickname())){            
            ComentarioDB.deleteComentario(id_comentario);
        }
        else {
            response.sendRedirect("not_found.jsp");
        }
        
        response.sendRedirect(url);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}
