/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package studyhub.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import studyhub.business.Tema;
import studyhub.business.Asignatura;
import studyhub.data.TemaDB;

/**
 *
 * @author daniel
 */
@WebServlet(name = "CreateTopic")
public class CreateTopic extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        //int idTema = ((Tema) session.getAttribute("tema")).getId_tema();
        
        int idTema = TemaDB.getSiguienteId();
        String titulo = request.getParameter("titulo");
        String mensaje_descripcion = request.getParameter("mensaje");
        String nickname = request.getRemoteUser();
        int idForo = ((Tema) session.getAttribute("tema")).getId_foro();
        TemaDB.crearTema(idTema, titulo, mensaje_descripcion, nickname, idForo);
       
        String url = "topic.jsp?idForo=" +idForo+ "&idTema="+idTema;
        response.sendRedirect(url);
    }
}
