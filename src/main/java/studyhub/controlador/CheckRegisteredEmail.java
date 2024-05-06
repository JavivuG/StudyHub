package studyhub.controlador;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import studyhub.data.UserDB;

@WebServlet(name = "CheckRegisteredEmail", urlPatterns = {"/CheckRegisteredEmail"}, asyncSupported = true)
public class CheckRegisteredEmail extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String email = (String) request.getParameter("email");
        
        if (email!=null){
            boolean emailExists = UserDB.emailExists(email);
            if (!emailExists){
                out.println("Este email no está registrado");
            }
        }
        out.flush(); 

        


    }
}
