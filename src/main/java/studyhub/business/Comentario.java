/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studyhub.business;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import studyhub.data.FicheroDB;

/**
 *
 * @author javier
 */
public class Comentario implements Serializable{
    private int id_comentario;
    private String texto;
    private LocalDateTime fecha_creacion;
    private int likes;
    private int dislikes;
    private int id_tema;
    private String nickname;

    public Comentario() {
        this.texto = "";
        this.nickname = "";
    }

    
    public Comentario(int id_comentario, String texto, LocalDateTime fecha_creacion, int likes, int dislikes, int id_tema, String nickname) {
        this.id_comentario = id_comentario;
        this.texto = texto;
        this.fecha_creacion=fecha_creacion;
        this.likes=likes;
        this.dislikes=dislikes;
        this.nickname = nickname;
        this.id_tema = id_tema;
    }
    
    public Comentario(int id_comentario, String texto, LocalDateTime fecha_creacion, int likes, int dislikes, User user,Tema tema) {
        this.id_comentario = id_comentario;
        this.texto = texto;
        this.fecha_creacion=fecha_creacion;
        this.likes=likes;
        this.dislikes=dislikes;
        this.nickname = user.getNickname();
        this.id_tema = tema.getId_tema();
    }

    
    public int getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(int id_comentario) {
        this.id_comentario = id_comentario;
    }



    public String getTexto() {
        return texto;
    }
    
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public void setNickname(User usuario) {
        this.nickname = usuario.getNickname();
    }

    public int getId_tema() {
        return id_tema;
    }

    public void setId_tema(int id_tema) {
        this.id_tema = id_tema;
    }
    
    public void setId_tema(Tema tema) {
        this.id_tema = tema.getId_tema();
    }

    public int getId_fichero() {
        return FicheroDB.getFicheroAsociado(id_comentario);
    }
    
    public String getNombreFichero(){
        int id_fichero=getId_fichero();
        if (id_fichero!=-1){
            return FicheroDB.getNombreFichero(id_fichero+"",id_comentario);
        }
        return "Error: No existe el fichero";
    }
    
    
    
    public String getTiempoPublicado(){
        String diferencia;
        LocalDateTime ahora = LocalDateTime.now();
        
        // Calcular la diferencia de tiempo entre ahora y fechaPasada
        Duration duracion = Duration.between(fecha_creacion, ahora);
        
        // Obtener la diferencia en horas y minutos
        long dias = duracion.toDays();
        long horas = duracion.toHours();
        long minutos = duracion.minusHours(horas).toMinutes();
        long segundos = duracion.minusMinutes(minutos).toSeconds();
        
        // Imprimir la diferencia de tiempo en formato "hace x horas" o "hace x minutos"
        if (dias > 0){
            diferencia="Hace "+ dias + " días";
        }
        else if (horas > 0){
            diferencia="Hace "+ horas + " horas";
            
            if (minutos > 0){
                diferencia=diferencia+" y "+ minutos +" minutos";
            }
        }
        else if (minutos > 0){
            diferencia="Hace "+ minutos + " minutos";

        }
        else if (segundos > 0) {
            diferencia="Hace "+ segundos +" segundos";
        }
        else {
            diferencia="Ahora mismo";
        }
        
        return diferencia;
    }
}
