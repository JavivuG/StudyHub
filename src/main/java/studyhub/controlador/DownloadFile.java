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

            String id_file = request.getParameter("idFichero");
            String id_comentario = request.getParameter("idComentario");
            String id_foro = request.getParameter("idForo");

            byte[] fileData = null;
            String fileName="";
            
            // Se desea descargar un fichero adjuntado a un comentario
            if (id_comentario!=null ){
                if(Integer.parseInt(id_comentario)!=-1){
                    fileData = FicheroDB.getFichero(id_file, Integer.parseInt(id_comentario));
                    fileName=FicheroDB.getNombreFichero(id_file,Integer.parseInt(id_comentario));
                }
                else {
                    if (Integer.parseInt(id_foro)!=-1){
                        fileData = FicheroDB.getFichero(id_file,-1);
                        fileName = FicheroDB.getNombreFichero(id_file,-1);                        
                    }
                    else {
                        response.sendRedirect("error.jsp");
                    }
                }
            }
            else {
                fileData = FicheroDB.getFichero(id_file,-1);
                fileName = FicheroDB.getNombreFichero(id_file,-1);
            }

            // Set response headers
            response.setContentType("application/octet-stream");
            
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
