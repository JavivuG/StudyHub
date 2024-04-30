/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package studyhub.controlador;


import studyhub.data.FicheroDB;
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
@WebServlet(name = "DeleteFile", urlPatterns = {"/DeleteFile"})
public class DeleteFile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parametros de la peticion
        
        String id_foro=request.getParameter("idForo");
        int id_fichero=Integer.parseInt(request.getParameter("idFichero"));
        String pagina=request.getParameter("page");

        
        if (request.isUserInRole("administrador") || request.isUserInRole("moderador") || FicheroDB.isOwner(id_fichero, request.getRemoteUser())){
            FicheroDB.deleteFichero(id_fichero);
        }
        else {
            response.sendRedirect("not_found.jsp");
        }
        
        String url=pagina+".jsp?idForo="+id_foro;
        
        response.sendRedirect(url);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}
