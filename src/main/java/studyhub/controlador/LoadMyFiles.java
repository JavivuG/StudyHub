/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package studyhub.controlador;

import studyhub.business.Asignatura;
import studyhub.data.FicheroDB;
import studyhub.data.ForoDB;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import studyhub.business.Fichero;
import studyhub.data.UserDB;



/**
 *
 * @author javi
 */
@WebServlet(name = "LoadMyFiles", urlPatterns = {"/LoadMyFiles"})
public class LoadMyFiles extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parametros de la peticion
        HttpSession session = request.getSession();

        ArrayList<Fichero> listaFicherosMios;
        String url="my_files.jsp";
        
        listaFicherosMios=FicheroDB.getFicherosUser(UserDB.selectUser(request.getRemoteUser()).getNickname());
        
        session.setAttribute("mis_ficheros", listaFicherosMios);
       
        response.sendRedirect(url);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

}
