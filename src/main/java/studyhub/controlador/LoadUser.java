/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package studyhub.controlador;

import studyhub.data.UserDB;
import studyhub.business.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 *
 * @author javi
 */
@WebServlet(name = "LoadUser", urlPatterns = {"/LoadUser"})
public class LoadUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parametros de la peticion
// Obtener los parametros de la peticion
        HttpSession session = request.getSession();
        
        UserDB.inicioSesion(request.getRemoteUser());
        User usuario=UserDB.selectUser(request.getRemoteUser());
        session.setAttribute("user", usuario);
        response.sendRedirect("profile.jsp");
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}
