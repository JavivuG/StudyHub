/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package studyhub.controlador;

import studyhub.business.Fichero;
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



/**
 *
 * @author javi
 */
@WebServlet(name = "LoadFiles", urlPatterns = {"/LoadFiles"})
public class LoadFiles extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parametros de la peticion
        HttpSession session = request.getSession();

        ArrayList<Fichero> listaFicheros;
        String id_foro=request.getParameter("idForo");
        String url="files.jsp?idForo="+id_foro;
        
        listaFicheros=FicheroDB.getFicheros(id_foro);
        Asignatura asignatura=ForoDB.getAsignatura(id_foro);
        
        session.setAttribute("asignatura", asignatura);
        session.setAttribute("ficheros", listaFicheros);
       
        response.sendRedirect(url);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}
