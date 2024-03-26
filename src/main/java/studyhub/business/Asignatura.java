/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studyhub.business;

/**
 *
 * @author subse
 */
public class Asignatura {

    private int id_asignatura;
    private String nombre;
    private String curso;
    
    public Asignatura(){
        this.nombre="";
        this.curso="";
    }

    public int getID_asignatura() {
        return id_asignatura;
    }

    public void setID_asignatura(int id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

}
