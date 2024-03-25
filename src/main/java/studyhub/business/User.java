package studyhub.business;


import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    private String nickname;
    private String password;
    private String nombre;
    private String apellidos;
    private String email;
    private LocalDate fecha_nacimiento;
    private LocalDate fecha_creacion;
    private String rol;

    public User(){
        this.nickname="";
        this.password="";
        this.nombre="";
        this.apellidos="";
        this.email="";
        this.fecha_creacion=LocalDate.now();
        this.rol="";
    }
    public User(String nickname, String password, String nombre, String apellidos, String email, LocalDate fecha_nacimiento, String rol) {
        this.nickname = nickname;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.fecha_creacion=LocalDate.now();
        this.fecha_nacimiento=fecha_nacimiento;
        this.rol=rol;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = LocalDate.parse(fecha_nacimiento);
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        if (rol.equals("estudiante") || rol.equals("profesor"))
            this.rol = rol;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion= LocalDate.parse(fecha_creacion);
    }
}
