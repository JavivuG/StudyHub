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
import studyhub.business.Comentario;

/**
 *
 * @author subse
 */
public class ComentarioDB {
    public static ArrayList<Comentario> getComentarios(String id_tema) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;

        query = "SELECT * FROM COMENTARIO WHERE id_tema=?";

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
}
