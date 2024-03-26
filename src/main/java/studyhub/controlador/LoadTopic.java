/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package studyhub.controlador;

import studyhub.business.Tema;
import studyhub.business.Comentario;
import studyhub.data.ComentarioDB;
import studyhub.data.TemaDB;
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
@WebServlet(name = "LoadTopic", urlPatterns = {"/LoadTopic"})
public class LoadTopic extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parametros de la peticion
        HttpSession session = request.getSession();

        ArrayList<Comentario> listaComentarios;
        String id_foro=request.getParameter("idForo");
        String id_tema=request.getParameter("idTema");
        Asignatura asignatura=ForoDB.getAsignatura(id_foro);
        Tema tema=TemaDB.getTema(id_tema);
        String url="topic.jsp?idForo="+id_foro+"&idTema="+id_tema;
        
        listaComentarios=ComentarioDB.getComentarios(id_tema);
        
        session.setAttribute("tema", tema);
        session.setAttribute("asignatura", asignatura);
        session.setAttribute("comentarios", listaComentarios);
       
        response.sendRedirect(url);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}
