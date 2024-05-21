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
import java.util.Date;
import studyhub.business.VotoComentario;

/**
 *
 * @author javi
 */
public class VotoComentarioDB {
    public static int getLikes(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;

        query = "SELECT * FROM votos_comentario WHERE id_comentario=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();

            int likes = 0;

            while (rs.next()) {
                if (rs.getInt("vote") == 1)
                    likes += rs.getInt("vote");
            }

            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return likes;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getDislikes(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;

        query = "SELECT * FROM votos_comentario WHERE id_comentario=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();

            int dislikes = 0;

            while (rs.next()) {
                if (rs.getInt("vote") == -1)
                    dislikes += rs.getInt("vote");
            }

            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return Math.abs(dislikes);

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int addVote(VotoComentario voto) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps, ps2;
        ResultSet rs = null;

        String query = "INSERT INTO votos_comentario (nickname, id_comentario, vote, created_at)  VALUES (?,?,?,?)";
        String query2 = "SELECT * FROM votos_comentario WHERE nickname=? AND id_comentario=?";

        Timestamp timestamp = new Timestamp(new Date().getTime());

        try {
            ps2 = connection.prepareStatement(query2);
            ps2.setString(1, voto.getNickname());
            ps2.setInt(2, voto.getId_comentario());
            rs = ps2.executeQuery();

            // If the user has already voted, and the vote is the same, remove the vote
            if (rs.next()) {
                if (rs.getInt("vote") == voto.getVote()) {
                    query = "DELETE FROM votos_comentario WHERE nickname=? AND id_comentario=?";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, voto.getNickname());
                    ps.setInt(2, voto.getId_comentario());
                    ps.executeUpdate();
                    ps.close();
                    pool.freeConnection(connection);
                    return 1;
                } else {
                    query = "UPDATE votos_comentario SET vote=?, created_at=? WHERE nickname=? AND id_comentario=?";
                    ps = connection.prepareStatement(query);
                    ps.setInt(1, voto.getVote());
                    ps.setTimestamp(2, timestamp);
                    ps.setString(3, voto.getNickname());
                    ps.setInt(4, voto.getId_comentario());
                    ps.executeUpdate();
                    ps.close();
                    pool.freeConnection(connection);
                    return 1;
                }
            } else {

                ps = connection.prepareStatement(query);
                ps.setString(1, voto.getNickname());
                ps.setInt(2, voto.getId_comentario());
                ps.setInt(3, voto.getVote());
                ps.setTimestamp(4, timestamp);

                ps.executeUpdate();
                ps.close();

                pool.freeConnection(connection);

                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
     public static int loggedUserHasLiked(String user, int comentarioId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        ResultSet rs = null;

        String query = "SELECT * FROM votos_comentario WHERE nickname=? AND id_comentario=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setInt(2, comentarioId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("vote");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
