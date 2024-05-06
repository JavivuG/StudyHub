package studyhub.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import studyhub.data.TokenDB;
import studyhub.data.UserDB;

@WebServlet(name = "ResetPassword", urlPatterns = {"/ResetPassword"})
public class ResetPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String token = request.getParameter("token");
        String url;
        
        if (TokenDB.isTokenValid(token)){
            UserDB.updatePassword(TokenDB.getUserToken(token), TokenDB.getPassword(token));
            TokenDB.marcarTokenUsado(token);
            url="dashboard.jsp";
        }
        else { // Token no es valido
            url="error.jsp";
        }
        
        response.sendRedirect(url);
            
    }
}
