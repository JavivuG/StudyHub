/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package studyhub.controlador;


import studyhub.data.TemaDB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author javi
 */
@WebServlet(name = "DeleteTopic", urlPatterns = {"/DeleteTopic"})
public class DeleteTopic extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parametros de la peticion
        String id_foro=request.getParameter("idForo");
        int id_tema=Integer.parseInt(request.getParameter("idTema"));
        
        if (request.isUserInRole("administrador") || request.isUserInRole("moderador")){
            TemaDB.deleteTema(id_tema);
        }
        else {
            response.sendRedirect("not_found.jsp");
        }
        
        String url="forum.jsp?idForo="+id_foro;
        response.sendRedirect(url);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}
