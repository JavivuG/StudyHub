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
import java.util.ArrayList;
import java.util.Date;
import jdk.internal.org.jline.terminal.TerminalBuilder;
import studyhub.business.Comentario;

/**
 *
 * @author javi
 */
public class ComentarioDB {
    public static ArrayList<Comentario> getComentarios(String id_tema) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;

        query = "SELECT * FROM comentario WHERE id_tema=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id_tema);
            rs = ps.executeQuery();
            Comentario comentario = null;
            ArrayList<Comentario> listaComentarios=new ArrayList<>();
            Timestamp timestamp;

            while (rs.next()) {
                comentario = new Comentario();
                comentario.setId_tema(rs.getInt("id_comentario"));
                comentario.setTexto(rs.getString("texto"));
                timestamp=rs.getTimestamp("fecha_creacion");
                comentario.setFecha_creacion(timestamp.toLocalDateTime());
                comentario.setLikes(rs.getInt("likes"));
                comentario.setDislikes(rs.getInt("dislikes"));
                comentario.setNickname(rs.getString("nickname"));
                comentario.setId_tema(rs.getInt("id_tema"));
                listaComentarios.add(comentario);
            }

            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return listaComentarios;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
}
    public static void setComentario(String chat, int idTema, String nickname) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query = "INSERT INTO comentario (texto, fecha_creacion, likes, dislikes, id_tema, nickname)  VALUES (?,?,?,?,?,?)";
        

        try { 
            if(chat != null && chat != ""){
                Timestamp timestamp = new Timestamp(new Date().getTime());
                int valor = 0;
                ps = connection.prepareStatement(query);
                ps.setString(1, chat);
                ps.setTimestamp(2, timestamp);
                ps.setInt(3, valor);
                ps.setInt(4, valor);
                ps.setInt(5, idTema);
                ps.setString(6, nickname);

                ps.executeUpdate();
                ps.close();
             }

            
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }
    
    public static void deleteComentario(int id_comentario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query;

        query = "DELETE FROM comentario c WHERE c.id_comentario= ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id_comentario);
            ps.executeUpdate();

            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
