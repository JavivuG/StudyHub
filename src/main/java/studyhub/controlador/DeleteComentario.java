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
import studyhub.data.ComentarioDB;



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
        String id_foro=request.getParameter("idForo");
        int id_tema=Integer.parseInt(request.getParameter("idTema"));
        int id_comentario=Integer.parseInt(request.getParameter("idComentario"));
        ComentarioDB.deleteComentario(id_comentario);
        
        String url="topic.jsp?idForo="+id_foro+"&idTema="+id_tema;
        
        response.sendRedirect(url);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}
