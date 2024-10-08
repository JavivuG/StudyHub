package studyhub.data;

import studyhub.controlador.EmailValidator;
import studyhub.business.User;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import studyhub.business.AportacionUsuario;

public class UserDB {
    public static int register(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps1=null;
        String query="INSERT INTO usuario (nickname, password, nombre, apellidos, email, fecha_nacimiento, fecha_creacion)      VALUES (?, ?, ?, ?, ?, ?, ?)";
        String queryRol="INSERT INTO rol (nickname, email, rol) VALUES (?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps1=connection.prepareStatement(queryRol);
            ps.setString(1, user.getNickname());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNombre());
            ps.setString(4, user.getApellidos());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getFecha_nacimiento().toString());
            ps.setString(7, user.getFecha_creacion().toString());
            ps1.setString(1, user.getNickname());
            ps1.setString(2, user.getEmail());
            ps1.setString(3, user.getRol());
            int res = ps.executeUpdate();
            int res1 = ps1.executeUpdate();
            ps1.close();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean userExists(String nickname) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM usuario WHERE nickname = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nickname);
            rs = ps.executeQuery();
            boolean res = rs.next();
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean emailExists(String emailAddress) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM usuario WHERE email = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, emailAddress);
            rs = ps.executeQuery();
            boolean res = rs.next();
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User selectUser(String userOrEmail) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query, query_rol;
        if (EmailValidator.validate(userOrEmail)){
            query = "SELECT * FROM usuario WHERE email = ?";
        }
        else {
            query = "SELECT * FROM usuario WHERE nickname = ?";
        }

        query_rol="SELECT * FROM rol WHERE nickname= ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userOrEmail);
            rs = ps.executeQuery();
            User user = null;
            
            if (rs.next()) {
                user = new User();
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("password"));
                user.setNombre(rs.getString("nombre"));
                user.setApellidos(rs.getString("apellidos"));
                user.setEmail(rs.getString("email"));

                user.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                user.setFecha_creacion(rs.getString("fecha_creacion"));
                ps=connection.prepareStatement(query_rol);

                ps.setString(1, user.getNickname());
                rs=ps.executeQuery();

                if (rs.next()){
                    user.setRol(rs.getString("rol"));
                }

            }

            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void updatePassword(String nickname, String password) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query;
        
        query = "UPDATE usuario SET password = ? WHERE nickname = ?";
        
         try {
            ps = connection.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, nickname);
            ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    
        public static ArrayList<AportacionUsuario> getTopUsers() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT\n" +
            "u.nickname,\n" +
            "u.nombre, u.apellidos, u.email, u.fecha_nacimiento, u.fecha_creacion,\n" +
            "(\n" +
            "(SELECT COUNT(ff.id_fichero) FROM fichero_foro ff WHERE ff.nickname=u.nickname)+\n" +
            "(SELECT COUNT(fc.id_fichero) FROM fichero_comentario fc WHERE fc.nickname=u.nickname)\n" +
            ") AS ficheros_subidos,\n" +
            "(SELECT COUNT(c.id_comentario) FROM comentario c WHERE c.nickname=u.nickname) AS comentarios_publicados\n" +
            "FROM usuario u\n" +
            "GROUP BY u.nickname";
        
        ArrayList<AportacionUsuario> top_usuarios=new ArrayList<>();

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()){
                AportacionUsuario datos_usuario=new AportacionUsuario();
                User user=new User();
                user.setNickname(rs.getString("nickname"));
                user.setNombre(rs.getString("nombre"));
                user.setApellidos(rs.getString("apellidos"));
                user.setEmail(rs.getString("email"));
                user.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                user.setFecha_creacion(rs.getString("fecha_creacion"));
                
                datos_usuario.setUser(user);
                datos_usuario.setArchivos_subidos(rs.getInt("ficheros_subidos"));
                datos_usuario.setComentarios(rs.getInt("comentarios_publicados"));
                
                top_usuarios.add(datos_usuario);
            }
            
            rs.close();
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return top_usuarios;

    }
}