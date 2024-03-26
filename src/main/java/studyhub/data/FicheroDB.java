/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studyhub.data;

import studyhub.business.Fichero;
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
public class FicheroDB {
    public static ArrayList<Fichero> getFicheros(String id_foro) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;
        
        query = "SELECT * FROM FICHERO WHERE id_foro=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id_foro);
            rs = ps.executeQuery();
            Fichero fichero = null;
            ArrayList<Fichero> listaFicheros=new ArrayList<>();
            Timestamp timestamp;
            
            while (rs.next()) {
                fichero = new Fichero();
                fichero.setId_fichero(rs.getInt("id_fichero"));
                fichero.setNombre(rs.getString("nombre"));
                fichero.setTipo(rs.getString("tipo"));
                timestamp=rs.getTimestamp("fecha_publicacion");
                fichero.setFecha_publicacion(timestamp.toLocalDateTime());
                fichero.setNickname(rs.getString("nickname"));
                fichero.setId_foro(rs.getInt("id_foro"));
                
                listaFicheros.add(fichero);
            }
            
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return listaFicheros;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    } 
}
