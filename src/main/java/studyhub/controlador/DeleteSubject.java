/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package studyhub.controlador;


import studyhub.data.ForoDB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteSubject", urlPatterns = {"/DeleteSubject"})
public class DeleteSubject extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parametros de la peticion

        System.out.println("DeleteSubject");
        String id = request.getParameter("idSubject");
    
        System.out.println("id: "+id);
        
        if (request.isUserInRole("administrador") || request.isUserInRole("moderador")){
            ForoDB.deleteAsignatura(id);
        }
        else {
            response.sendRedirect("not_found.jsp");
        }
        
        String url="subjects.jsp";
        response.sendRedirect(url);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}
