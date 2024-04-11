package studyhub.controlador;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import studyhub.data.UserDB;

@WebServlet(name = "CheckDuplicateServlet", urlPatterns = {"/CheckDuplicateServlet"}, asyncSupported = true)
public class CheckDuplicateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String username = (String) request.getParameter("nickname");
        String email = (String) request.getParameter("email");

        if (username!=null){
            boolean userExists = UserDB.userExists(username);
            if (userExists){
                out.println("Este nickname ya está en uso");
            }
        }
        
        if (email!=null){
            boolean emailExists = UserDB.emailExists(email);
            if (emailExists){
                out.println("Este email ya está en uso");
            }
        }
        out.flush(); 

        


    }
}
