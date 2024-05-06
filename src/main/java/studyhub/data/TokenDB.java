/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studyhub.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author javier
 */
public class TokenDB {

    public static void generateToken(String nickname, String password_temporal, String token , LocalDateTime tiempo_valido) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query;

        query = "INSERT INTO reset_tokens (nickname, password ,token, tiempo_validez) VALUES (?, ?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nickname);
            ps.setString(2, password_temporal);
            ps.setString(3, token);
            ps.setTimestamp(4, Timestamp.valueOf(tiempo_valido));
            ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean isTokenValid(String token) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query;
        boolean isValid=false;
        
        query = "SELECT * FROM reset_tokens WHERE token = ? AND tiempo_validez > NOW() AND usado = FALSE";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            rs = ps.executeQuery();
            isValid = rs.next();
            ps.close();
            pool.freeConnection(connection);
            return isValid;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return isValid;
    }
    
    public static String getUserToken(String token) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query;
        String user=null;
        
        query = "SELECT nickname FROM reset_tokens WHERE token = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = rs.getString("nickname");
            }
            ps.close();
            pool.freeConnection(connection);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public static String getPassword(String token) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query;
        String password=null;
        
        query = "SELECT password FROM reset_tokens WHERE token = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                password = rs.getString("password");
            }
            ps.close();
            pool.freeConnection(connection);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return password;
    }
    
    public static void marcarTokenUsado(String token) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query;
        
        query = "UPDATE reset_tokens SET usado = TRUE WHERE token = ?";
        
         try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void deleteToken(String token) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query;
        
        query = "DELETE FROM reset_tokens WHERE token = ?";
        
         try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}