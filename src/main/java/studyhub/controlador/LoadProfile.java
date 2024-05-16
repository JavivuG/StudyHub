/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package studyhub.controlador;

import studyhub.business.Asignatura;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import studyhub.business.Comentario;
import studyhub.business.ContribucionAsignatura;
import studyhub.business.Fichero;
import studyhub.business.Tema;
import studyhub.business.User;
import studyhub.data.ComentarioDB;
import studyhub.data.FicheroDB;
import studyhub.data.ForoDB;
import studyhub.data.TemaDB;
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
        int MAX_ASIGNATURAS=200;
        
        ArrayList<Fichero> ficherosUsuario=null;
        User usuario=UserDB.selectUser(request.getRemoteUser());
        ficherosUsuario=FicheroDB.getFicherosUserLimit(usuario.getNickname());
        
        
        // Almacena los datos en el alcance de la solicitud
        session.setAttribute("user", usuario);
        session.setAttribute("ficheros_usuario", ficherosUsuario);
        
        Comparator<ContribucionAsignatura> comparadorPorcentajeContribucion = new Comparator<ContribucionAsignatura>() {
            @Override
            public int compare(ContribucionAsignatura contribucion1, ContribucionAsignatura contribucion2) {
                // Compara los porcentajes de contribución de forma descendente
                return Integer.compare(contribucion2.getPorcentajeContribucion(), contribucion1.getPorcentajeContribucion());
            }
        };
        
        ArrayList<ContribucionAsignatura> contribuciones_usuario=ForoDB.getContribucionAsignaturas(usuario.getNickname());

        contribuciones_usuario.sort(comparadorPorcentajeContribucion);
        
        Iterator<ContribucionAsignatura> iterador = contribuciones_usuario.iterator();
        while (iterador.hasNext()) {
            ContribucionAsignatura contribucionActual = iterador.next();
            if (contribucionActual.getContr_total() == 0 || contribucionActual.getContr_usuario() == 0) {
                iterador.remove();
            }
        }
        
        ArrayList<ContribucionAsignatura> contr_usuario_def=new ArrayList<>();
        int iteracion=(contribuciones_usuario.size()<4) ? contribuciones_usuario.size() : 4;
        for(int i=0; i<iteracion; i++){
            contr_usuario_def.add(contribuciones_usuario.get(i));
        }
        

        session.setAttribute("contribuciones", contr_usuario_def);
        
          ArrayList<Asignatura> asignaturas=null;
        asignaturas=ForoDB.getAsignaturas(MAX_ASIGNATURAS);
        session.setAttribute("asignaturas", asignaturas);
        
        
        ArrayList<Comentario> comentarios= null;
        comentarios=ComentarioDB.getComents();
        session.setAttribute("comentarios", comentarios);
        
        
        ArrayList<Tema> temas= null;
        temas=TemaDB.getTodosTemas();
        session.setAttribute("temas", temas);
        
        response.sendRedirect("profile.jsp");
    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}
