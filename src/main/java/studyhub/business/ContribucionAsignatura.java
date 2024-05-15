package studyhub.business;

import java.io.Serializable;

public class ContribucionAsignatura implements Serializable {
    private Asignatura foro;
    private int contr_usuario;
    private int contr_total;

    public ContribucionAsignatura() {
        this.foro = null;
        this.contr_usuario=0;
        this.contr_total=0;
    }

    public String getNombreForo() {
        return foro.getNombre();
    }

    public Asignatura getForo() {
        return foro;
    }

    public void setForo(Asignatura foro) {
        this.foro = foro;
    }

    public int getContr_usuario() {
        return contr_usuario;
    }

    public void setContr_usuario(int contr_usuario) {
        this.contr_usuario = contr_usuario;
    }

    public int getContr_total() {
        return contr_total;
    }

    public void setContr_total(int contr_total) {
        this.contr_total = contr_total;
    }
    
    
    public int getPorcentajeContribucion(){
        if (contr_total == 0){
            return 0;
        }
        return (int) ( ((double) contr_usuario) / contr_total * 100);
    }

}