package studyhub.controlador;

import studyhub.data.FicheroDB;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import studyhub.business.Asignatura;
import studyhub.business.Fichero;
import studyhub.data.ForoDB;

@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String id_foro = request.getParameter("idForo");
        String nombre = request.getParameter("nombre");
        Part filePart = request.getPart("file");
        InputStream inputStream = null; 

        if (filePart != null) inputStream = filePart.getInputStream();
        
        Fichero fichero = new Fichero();
        fichero.setNombre(nombre);
        fichero.setTipo(filePart.getContentType());
        fichero.setNickname(request.getRemoteUser());
        fichero.setId_foro(Integer.parseInt(id_foro));
                
        int res = FicheroDB.uploadFile(fichero, inputStream);
        
        String url;
        
        if (res > 0) {
            ArrayList<Fichero> listaFicheros;
            listaFicheros=FicheroDB.getFicheros(id_foro);
            Asignatura asignatura=ForoDB.getAsignatura(id_foro);
        
            session.setAttribute("asignatura", asignatura);
            session.setAttribute("ficheros", listaFicheros);
            session.setAttribute("upload", true);
            url="files.jsp?idForo="+id_foro;
        } else {
            session.setAttribute("upload", false);
            url = "upload.jsp";
        }
        response.sendRedirect(url);

    }
}
