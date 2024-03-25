package studyhub.controlador;

import studyhub.business.User;
import studyhub.data.UserDB;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Register")
public class RegisterUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener los parametros de la peticion
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String nickname= request.getParameter("nickname");
        String password= request.getParameter("password");
        String fecha_nacimiento = request.getParameter("fnacimiento");
        String rol = request.getParameter("rol");

        // Crear objeto usuario
        User user = new User();
        user.setNombre(nombre);
        user.setApellidos(apellidos);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setPassword(password);
        user.setFecha_nacimiento(fecha_nacimiento);
        user.setRol(rol);

        // Guardar objeto user en la sesion
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        int res=UserDB.register(user);
        if (res > 0){
            System.out.println("Usuario registrado satisfactoriamente");
        }
        else {
            System.out.println("Error al registrar usuario");
        }
        // forward request and response to JSP page
        String url = "/dashboard.jsp";
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}