/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studyhub.data;

import studyhub.business.Tema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author javier
 */
public class TemaDB {
    public static ArrayList<Tema> getTemas(String id_foro) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;

        query = "SELECT * FROM TEMA WHERE id_foro=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id_foro);
            rs = ps.executeQuery();
            Tema tema = null;
            ArrayList<Tema> listaTemas=new ArrayList<>();
            Timestamp timestamp;

            while (rs.next()) {
                tema = new Tema();
                tema.setId_tema(rs.getInt("id_tema"));
                tema.setTitulo(rs.getString("titulo"));
                tema.setDescripcion(rs.getString("descripcion"));
                timestamp=rs.getTimestamp("fecha_publicacion");
                tema.setFecha_publicacion(timestamp.toLocalDateTime());
                tema.setLikes(rs.getInt("likes"));
                tema.setDislikes(rs.getInt("dislikes"));
                tema.setNickname(rs.getString("nickname"));
                tema.setId_foro(rs.getInt("id_foro"));
                listaTemas.add(tema);
            }

            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return listaTemas;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Tema getTema(String id_tema) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;

        query = "SELECT * FROM TEMA WHERE id_tema=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id_tema);
            rs = ps.executeQuery();
            Tema tema = null;
            Timestamp timestamp;

            if (rs.next()) {
                tema = new Tema();
                tema.setId_tema(rs.getInt("id_tema"));
                tema.setTitulo(rs.getString("titulo"));
                tema.setDescripcion(rs.getString("descripcion"));
                timestamp=rs.getTimestamp("fecha_publicacion");
                tema.setFecha_publicacion(timestamp.toLocalDateTime());
                tema.setLikes(rs.getInt("likes"));
                tema.setDislikes(rs.getInt("dislikes"));
                tema.setNickname(rs.getString("nickname"));
                tema.setId_foro(rs.getInt("id_foro"));
            }

            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return tema;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static ArrayList<Tema> getTemasRecientes(String id_foro, int max_temas) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;

        query = "SELECT * FROM TEMA WHERE id_foro=? ORDER BY fecha_publicacion DESC LIMIT ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id_foro);
            ps.setInt(2, max_temas);
            rs = ps.executeQuery();
            Tema tema = null;
            ArrayList<Tema> listaTemas=new ArrayList<>();
            Timestamp timestamp;

            while (rs.next()) {
                tema = new Tema();
                tema.setId_tema(rs.getInt("id_tema"));
                tema.setTitulo(rs.getString("titulo"));
                tema.setDescripcion(rs.getString("descripcion"));
                timestamp=rs.getTimestamp("fecha_publicacion");
                tema.setFecha_publicacion(timestamp.toLocalDateTime());
                tema.setLikes(rs.getInt("likes"));
                tema.setDislikes(rs.getInt("dislikes"));
                tema.setNickname(rs.getString("nickname"));
                tema.setId_foro(rs.getInt("id_foro"));
                listaTemas.add(tema);
            }

            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return listaTemas;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
     public static ArrayList<Tema> getTemasDestacados(String id_foro, int max_temas) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;

        query = "SELECT t.*, COUNT(c.id_tema) as num_referencias FROM TEMA t LEFT JOIN COMENTARIO c ON c.id_tema=t.id_tema WHERE id_foro= ? GROUP BY t.id_tema ORDER BY num_referencias DESC LIMIT ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id_foro);
            ps.setInt(2, max_temas);
            rs = ps.executeQuery();
            Tema tema = null;
            ArrayList<Tema> listaTemas=new ArrayList<>();
            Timestamp timestamp;

            while (rs.next()) {
                tema = new Tema();
                tema.setId_tema(rs.getInt("id_tema"));
                tema.setTitulo(rs.getString("titulo"));
                tema.setDescripcion(rs.getString("descripcion"));
                timestamp=rs.getTimestamp("fecha_publicacion");
                tema.setFecha_publicacion(timestamp.toLocalDateTime());
                tema.setLikes(rs.getInt("likes"));
                tema.setDislikes(rs.getInt("dislikes"));
                tema.setNickname(rs.getString("nickname"));
                tema.setId_foro(rs.getInt("id_foro"));
                listaTemas.add(tema);
            }

            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return listaTemas;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
