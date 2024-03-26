/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studyhub.business;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author javier
 */
public class Tema implements Serializable {
    private int id_tema;
    private String titulo;
    private String descripcion;
    private LocalDateTime fecha_publicacion;
    private int likes;
    private int dislikes;
    private String nickname;
    private int id_foro;

    public Tema() {
        this.titulo="";
        this.descripcion="";
        this.likes=0;
        this.dislikes=0;
        this.nickname="";
    }

    
    public Tema(int id_tema, String titulo, String descripcion, LocalDateTime fecha_publicacion, int likes, int dislikes,String nickname, int id_foro) {
        this.id_tema=id_tema;
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.fecha_publicacion=fecha_publicacion;
        this.likes=likes;
        this.dislikes=dislikes;
        this.nickname = nickname;
        this.id_foro = id_foro;
    }

    public Tema(int id_tema, String titulo, String descripcion, LocalDateTime fecha_publicacion, int likes, int dislikes, User user, Asignatura foro) {
        this.id_tema=id_tema;
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.fecha_publicacion=fecha_publicacion;
        this.likes=likes;
        this.dislikes=dislikes;
        this.nickname = user.getNickname();
        this.id_foro = foro.getID_asignatura();
    }

    public int getId_tema() {
        return id_tema;
    }

    public void setId_tema(int id_tema) {
        this.id_tema = id_tema;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(LocalDateTime fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getId_foro() {
        return id_foro;
    }

    public void setId_foro(int id_foro) {
        this.id_foro = id_foro;
    }
    
    public String getTiempoPublicado(){
        String diferencia;
        LocalDateTime ahora = LocalDateTime.now();
        
        // Calcular la diferencia de tiempo entre ahora y fechaPasada
        Duration duracion = Duration.between(fecha_publicacion, ahora);
        
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
            
            if (minutos > 0){
                diferencia=diferencia+" y "+ segundos +" segundos";
            }
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
