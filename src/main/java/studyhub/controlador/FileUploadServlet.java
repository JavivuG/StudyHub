package studyhub.controlador;

import studyhub.data.FicheroDB;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

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
import studyhub.data.ComentarioDB;
import studyhub.data.ForoDB;
import studyhub.data.UserDB;

@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String id_foro = request.getParameter("idForo");
        String id_tema=request.getParameter("idTema");
        Part filePart = request.getPart("file");
        String filename = filePart.getSubmittedFileName();
        String nickname=UserDB.selectUser(request.getRemoteUser()).getNickname();
        InputStream inputStream = null; 

        if (filePart != null) inputStream = filePart.getInputStream();
        
        Fichero fichero = new Fichero();
        fichero.setNombre(filename);
        fichero.setTipo(filePart.getContentType());
        fichero.setNickname(nickname);
        String url;

        if (request.getParameter("idTema") != null && !request.getParameter("idTema").isEmpty()){
            int id_comentario=ComentarioDB.setComentario(Integer.parseInt(id_tema), nickname);
            fichero.setId_comentario(id_comentario);
            int resultado[]=FicheroDB.uploadFile(fichero, inputStream);

        
            if (resultado[0] > 0) {
                ComentarioDB.addFichero(id_comentario, resultado[1]);
                url="topic.jsp?idForo="+id_foro+"&idTema="+id_tema;
            } else {
                ComentarioDB.deleteComentario(id_comentario);
                url = "upload.jsp?idForo="+id_foro+"&idTema="+id_tema;
            }
        }
        else {
            fichero.setId_foro(Integer.parseInt(id_foro));
            
            int resultado[] = FicheroDB.uploadFile(fichero, inputStream);
            fichero.setId_fichero(resultado[1]);
        

            if (resultado[0] > 0) {
                ArrayList<Fichero> listaFicheros;
                listaFicheros=FicheroDB.getFicheros(id_foro);
                Asignatura asignatura=ForoDB.getAsignatura(id_foro);

                session.setAttribute("asignatura", asignatura);
                session.setAttribute("ficheros", listaFicheros);
                session.setAttribute("subida", true);
                url="files.jsp?idForo="+id_foro;
            } else {
                session.setAttribute("subida", false);
                url = "upload.jsp?idForo="+id_foro;
            }
        }
                
        response.sendRedirect(url);
    }
}
