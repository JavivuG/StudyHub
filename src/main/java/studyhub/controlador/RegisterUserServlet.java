package studyhub.controlador;

import studyhub.business.User;
import studyhub.data.UserDB;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/Register")
public class RegisterUserServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                HttpSession session = request.getSession();

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



        int res=UserDB.register(user);
        String url;

        if (res > 0) {
            // Usuario registrado satisfactoriamente
            session.setAttribute("estado_registro", true);
            url="registered.jsp";
        } else {
            // Error al registrar usuario
            url = "signup.jsp";
        }
        // forward request and response to JSP page
        response.sendRedirect(url);
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}