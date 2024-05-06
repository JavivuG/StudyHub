package studyhub.data;

import studyhub.controlador.EmailValidator;
import studyhub.business.User;
import java.util.Date;
import java.sql.*;

public class UserDB {
    public static int register(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps1=null;
        String query="INSERT INTO usuario (nickname, password, nombre, apellidos, email, fecha_nacimiento, fecha_creacion)      VALUES (?, ?, ?, ?, ?, ?, ?)";
        String queryRol="INSERT INTO rol VALUES (?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps1=connection.prepareStatement(queryRol);
            ps.setString(1, user.getNickname());
            ps1.setString(1, user.getNickname());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNombre());
            ps.setString(4, user.getApellidos());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getFecha_nacimiento().toString());
            ps.setString(7, user.getFecha_creacion().toString());
            ps1.setString(2, user.getRol());
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
    
    public static void inicioSesion(String user){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        Timestamp timestamp = new Timestamp(new Date().getTime());
        
        String query="UPDATE usuario SET ultimo_inicio = ? WHERE nickname = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setTimestamp(1, timestamp);
            ps.setString(2, user);            
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
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
    
}