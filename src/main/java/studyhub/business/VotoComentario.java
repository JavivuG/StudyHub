package studyhub.business;


import java.io.Serializable;
import java.time.LocalDate;

public class VotoComentario implements Serializable {
    private String nickname;
    private int vote;
    private int id_comentario;
    private LocalDate created_at;

    public VotoComentario(){
        this.nickname="";
        this.vote=0;
        this.id_comentario=0;
        this.created_at=LocalDate.now();
    }

    public VotoComentario(String nickname, int vote, int id_comentario) {
        this.nickname = nickname;
        this.vote = vote;
        this.id_comentario = id_comentario;
        this.created_at=LocalDate.now();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(int id_comentario) {
        this.id_comentario = id_comentario;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }
}
