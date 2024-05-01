/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studyhub.business;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 *
 * @author javier
 */
public class Fichero implements Serializable{
    private int id_fichero;
    private String nombre;
    private String tipo;
    private LocalDateTime fecha_publicacion;
    private String nickname;
    private int id_foro;

    public Fichero() {
        this.nombre = "";
        this.tipo = "";
        this.nickname = "";
    }

    
    public Fichero(int id_fichero, String nombre, String tipo, String nickname, int id_foro) {
        this.id_fichero = id_fichero;
        this.nombre = nombre;
        this.tipo = tipo;
        this.nickname = nickname;
        this.id_foro = id_foro;
    }

    public Fichero(int id_fichero, String nombre, String tipo, User user, Asignatura foro) {
        this.id_fichero = id_fichero;
        this.nombre = nombre;
        this.tipo = tipo;
        this.nickname = user.getNickname();
        this.id_foro = foro.getID_asignatura();
    }

    
    public int getId_fichero() {
        return id_fichero;
    }

    public void setId_fichero(int id_fichero) {
        this.id_fichero = id_fichero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public LocalDateTime getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(LocalDateTime fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
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

    public int getId_foro() {
        return id_foro;
    }

    public void setId_foro(int id_foro) {
        this.id_foro = id_foro;
    }
    
    public void setId_foro(Asignatura foro) {
        this.id_foro = foro.getID_asignatura();
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
