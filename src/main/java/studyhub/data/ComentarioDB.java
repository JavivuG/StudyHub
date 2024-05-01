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
import studyhub.business.Comentario;

/**
 *
 * @author javi
 */
public class ComentarioDB {
    public static ArrayList<Comentario> getComentarios(String id_tema) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null, ps2 = null;
        ResultSet rs = null, rs2 = null;
        String query, query2;
        int idComentario = 0;

        query = "SELECT * FROM comentario WHERE id_tema=?";
        query2 = "SELECT * FROM votos_comentario WHERE id_comentario=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id_tema);
            rs = ps.executeQuery();
            Comentario comentario = null;
            ArrayList<Comentario> listaComentarios=new ArrayList<>();
            Timestamp timestamp;

            while (rs.next()) {
                comentario = new Comentario();

                idComentario = rs.getInt("id_comentario");
                comentario.setId_comentario(idComentario);
                comentario.setTexto(rs.getString("texto"));
                timestamp=rs.getTimestamp("fecha_creacion");
                comentario.setFecha_creacion(timestamp.toLocalDateTime());
                comentario.setNickname(rs.getString("nickname"));
                comentario.setId_tema(rs.getInt("id_tema"));

                ps2 = connection.prepareStatement(query2);
                ps2.setInt(1, idComentario);
                rs2 = ps2.executeQuery();

                comentario.setLikes(0);
                comentario.setDislikes(0);

                while (rs2.next()) {
                    if (rs2.getInt("vote") == 1) {
                        comentario.setLikes(comentario.getLikes() + 1);
                    } else {
                        comentario.setDislikes(comentario.getDislikes() + 1);
                    }
                }

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
        String query = "INSERT INTO comentario (texto, fecha_creacion, id_tema, nickname)  VALUES (?,?,?,?)";
        

        try { 
            if(chat != null && chat != ""){
                Timestamp timestamp = new Timestamp(new Date().getTime());
                ps = connection.prepareStatement(query);
                ps.setString(1, chat);
                ps.setTimestamp(2, timestamp);
                ps.setInt(3, idTema);
                ps.setString(4, nickname);

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

    public static boolean isOwner(int id_comentario, String nickname) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;

        query = "SELECT * FROM comentario WHERE id_comentario=? AND nickname=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id_comentario);
            ps.setString(2, nickname);
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
}
