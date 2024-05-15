package studyhub.controlador;

import studyhub.business.User;
import studyhub.data.UserDB;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        String url;
        String hashedPassword = null;
        
         try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            byte[] hashedBytes = digest.digest(password.getBytes());
            
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }
            
            hashedPassword = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        if(nombre.length()<=50 && apellidos.length()<=100 && password.length()<=50 && email.length()<=50){
            // Crear objeto usuario
            User user = new User();
            user.setNombre(nombre);
            user.setApellidos(apellidos);
            user.setNickname(nickname);
            user.setEmail(email);
            user.setPassword(hashedPassword);
            user.setFecha_nacimiento(fecha_nacimiento);
            user.setRol(rol);

            int res=UserDB.register(user);

            if (res > 0) {
                // Usuario registrado satisfactoriamente
                session.setAttribute("estado_registro", true);
                url="registered.jsp";
            } else {
                // Error al registrar usuario
                url = "signup.jsp";
            }
        }
        else {
            url = "signup.jsp";
        }
        
        response.sendRedirect(url);

    }
    
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}