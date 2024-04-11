package studyhub.controlador;

import studyhub.data.FicheroDB;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/downloadServlet")
public class DownloadFile extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parametros de la peticion
        HttpSession session = request.getSession();
        
        try {
            String id_file = request.getParameter("file");
            byte[] fileData = FicheroDB.getFichero(id_file);

            // Set response headers
            response.setContentType("application/octet-stream");
            String fileName = FicheroDB.getNombreFichero(id_file);
            
            response.setHeader("Content-Disposition", "attachment; filename="+ fileName);

            // Write BLOB data to output stream
            response.getOutputStream().write(fileData);
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
        
    }
}
