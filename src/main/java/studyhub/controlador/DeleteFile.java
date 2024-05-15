/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package studyhub.controlador;


import studyhub.data.FicheroDB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import studyhub.data.ComentarioDB;
import studyhub.data.UserDB;


/**
 *
 * @author javi
 */
@WebServlet(name = "DeleteFile", urlPatterns = {"/DeleteFile"})
public class DeleteFile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parametros de la peticion
        
        String id_foro=request.getParameter("idForo");
        int id_fichero=Integer.parseInt(request.getParameter("idFichero"));
        String id_comentario=request.getParameter("idComentario");
        String pagina=request.getParameter("page");
        String url;
        String nickname=UserDB.selectUser(request.getRemoteUser()).getNickname();
        boolean esPropietario=false, esComentario=false;
        
        if (id_comentario!=null){
            if (Integer.parseInt(id_comentario)!=-1){
                esPropietario=FicheroDB.isOwner(id_fichero,nickname,Integer.parseInt(id_comentario));
                esComentario=true;
            }
            else {
                if (Integer.parseInt(id_foro)!=-1){
                    esPropietario=FicheroDB.isOwner(id_fichero, nickname,-1);
                    esComentario=false;
                }
                else {
                    response.sendRedirect("error.jsp");
                }
            }
        }
        else {
            esPropietario=FicheroDB.isOwner(id_fichero, nickname,-1);
            esComentario=false;
        }

        if (request.isUserInRole("administrador") || request.isUserInRole("moderador") || esPropietario){
            if(esComentario){
                FicheroDB.deleteFichero(id_fichero,Integer.parseInt(id_comentario));
                ComentarioDB.deleteComentario(Integer.parseInt(id_comentario));
            }
            else {
                FicheroDB.deleteFichero(id_fichero,-1);
            }
        }
        else {
            response.sendRedirect("not_found.jsp");
        }
        
        
        if (id_foro==null){
            url=pagina+".jsp";
        }
        else {
            url=pagina+".jsp?idForo="+id_foro;
        }
        
        response.sendRedirect(url);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}
