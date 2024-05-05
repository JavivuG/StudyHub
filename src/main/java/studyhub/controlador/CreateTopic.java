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
import studyhub.data.TemaDB;
import studyhub.data.UserDB;

/**
 *
 * @author daniel
 */
@WebServlet(name = "/CreateTopic", urlPatterns = {"/CreateTopic"}, asyncSupported = true)
public class CreateTopic extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String titulo = request.getParameter("title");
        String mensaje_descripcion = request.getParameter("message");
        
        String nickname = UserDB.selectUser(request.getRemoteUser()).getNickname();
        
        int idForo;
        idForo = Integer.parseInt(request.getParameter("idForo"));

        int idTema=TemaDB.crearTema(titulo, mensaje_descripcion, nickname, idForo);
       
        String url = "topic.jsp?idForo=" +idForo+ "&idTema="+idTema;
        response.sendRedirect(url);
    }
}
