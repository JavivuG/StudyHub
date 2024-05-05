/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package studyhub.controlador;

import studyhub.business.Asignatura;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import studyhub.business.Fichero;
import studyhub.data.FicheroDB;
import studyhub.data.ForoDB;
import studyhub.data.UserDB;

/**
 *
 * @author javi
 */
@WebServlet(name = "LoadProfile", urlPatterns = {"/LoadProfile"})
public class LoadProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parametros de la peticion
        HttpSession session = request.getSession();
        
        ArrayList<Fichero> ficherosUsuario=null;
        ficherosUsuario=FicheroDB.getFicherosUser(UserDB.selectUser(request.getRemoteUser()).getNickname());
        
        
        // Almacena los datos en el alcance de la solicitud
        session.setAttribute("ficheros_usuario", ficherosUsuario);
        response.sendRedirect("profile.jsp");
    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}
