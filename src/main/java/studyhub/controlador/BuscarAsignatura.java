package studyhub.controlador;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import studyhub.business.Asignatura;
import studyhub.data.ForoDB;

@WebServlet(name = "BuscarAsignatura", urlPatterns = {"/BuscarAsignatura"}, asyncSupported = true)
public class BuscarAsignatura extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String busqueda=(String) request.getParameter("q");
        ArrayList<Asignatura> asignaturasEncontradas=ForoDB.buscarAsignatura(busqueda);

        StringBuilder htmlBuilder = new StringBuilder();
        for (int i=0; i<asignaturasEncontradas.size(); i++) {
            Asignatura asignatura=asignaturasEncontradas.get(i);
            htmlBuilder.append("<div class=\"ag-courses_item\">");
            htmlBuilder.append("<a href=\"forum.jsp?idForo=").append(asignatura.getID_asignatura()).append("\" class=\"ag-courses-item_link\">");
            htmlBuilder.append("<div class=\"ag-courses-item_bg\"></div>");
            htmlBuilder.append("<div class=\"ag-courses-item_title\">").append(asignatura.getNombre()).append("</div>");
            htmlBuilder.append("<div class=\"ag-courses-item_description-box\">");
            htmlBuilder.append("<span class=\"ag-courses-item_description\">").append(asignatura.getCurso()).append("</span>");
            htmlBuilder.append("</div>");
            htmlBuilder.append("</a>");
            htmlBuilder.append("</div>");
        }

        response.setContentType("text/html");
        response.getWriter().print(htmlBuilder.toString());
    }
}
