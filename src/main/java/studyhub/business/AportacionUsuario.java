package studyhub.business;

import java.io.Serializable;

public class AportacionUsuario implements Serializable {
    private User usuario;
    private int archivos_subidos;
    private int comentarios;

    public AportacionUsuario() {
        this.usuario = null;
        this.archivos_subidos=0;
        this.comentarios=0;
    }

    public String getNickname() {
        return usuario.getNickname();
    }

    public User getUser() {
        return usuario;
    }

    public void setUser(User usuario) {
        this.usuario = usuario;
    }

    public int getArchivos_subidos() {
        return archivos_subidos;
    }

    public void setArchivos_subidos(int archivos_subidos) {
        this.archivos_subidos = archivos_subidos;
    }

    public int getComentarios() {
        return comentarios;
    }

    public void setComentarios(int comentarios) {
        this.comentarios = comentarios;
    }
    
    public String getMensaje(String userSesion){
        String mensaje = "";
        String mensajeFicheros="";
        String mensajeComentarios="";

        if (getNickname().equals(userSesion)) {
            mensaje += "Has ";
        } else {
            mensaje += "Ha ";
        }

        if (archivos_subidos == 1) {
            mensajeFicheros += "subido 1 fichero";
        } else if (archivos_subidos>1) {
            mensajeFicheros += "subido " + archivos_subidos + " ficheros";
        }

        if (comentarios == 1) {
            mensajeComentarios += "publicado 1 comentario";
        } else if (comentarios>1) {
            mensajeComentarios += "publicado "+ comentarios + " comentarios";
        }

        if (!mensajeComentarios.equals("") && !mensajeFicheros.equals("")){
            mensaje+=mensajeFicheros+" y "+mensajeComentarios;
        }
        else {
            mensaje+=(mensajeFicheros.equals("")) ? mensajeComentarios : mensajeFicheros;
        }
        
        return mensaje;
    }

}