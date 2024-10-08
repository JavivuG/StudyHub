/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studyhub.data;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import studyhub.business.Fichero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;
import java.sql.Statement;
import java.util.regex.*;  

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

        query = "SELECT f.* FROM fichero_foro f WHERE f.id_foro = ? ORDER BY f.fecha_publicacion DESC";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id_foro);
            rs = ps.executeQuery();
            ArrayList<Fichero> listaFicheros = new ArrayList<>();
            Timestamp timestamp;

            while (rs.next()) {
                Fichero fichero = new Fichero(rs.getInt("id_fichero"));
                fichero.setNombre(rs.getString("nombre"));
                fichero.setTipo(rs.getString("tipo"));
                timestamp = rs.getTimestamp("fecha_publicacion");
                fichero.setFecha_publicacion(timestamp.toLocalDateTime());
                fichero.setNickname(rs.getString("nickname"));
                fichero.setId_foro(rs.getInt("id_foro"));
                
                listaFicheros.add(fichero);
            }

            return listaFicheros;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                pool.freeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static ArrayList<Fichero> getFicherosRecientes(String id_foro, int max_ficheros) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;

        query = "SELECT f.* FROM fichero_foro f WHERE f.id_foro = ? ORDER BY f.fecha_publicacion DESC LIMIT ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id_foro);
            ps.setInt(2, max_ficheros);
            rs = ps.executeQuery();
            ArrayList<Fichero> listaFicheros = new ArrayList<>();
            Timestamp timestamp;

            while (rs.next()) {
                Fichero fichero = new Fichero(rs.getInt("id_fichero"));
                fichero.setNombre(rs.getString("nombre"));
                fichero.setTipo(rs.getString("tipo"));
                timestamp = rs.getTimestamp("fecha_publicacion");
                fichero.setFecha_publicacion(timestamp.toLocalDateTime());
                fichero.setNickname(rs.getString("nickname"));
                fichero.setId_foro(rs.getInt("id_foro"));

                listaFicheros.add(fichero);
            }

            return listaFicheros;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                pool.freeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    public static ArrayList<Fichero> getFicherosUserLimit(String nickname) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query;

        query = "SELECT 'fichero_foro' AS tabla_fuente, id_fichero, id_foro, nombre, tipo, file, fecha_publicacion, nickname\n" +
                "FROM fichero_foro\n" +
                "WHERE nickname = ?\n" +
                "UNION ALL\n" +
                "SELECT 'fichero_comentario' AS tabla_fuente, id_fichero, id_comentario, nombre, tipo, file, fecha_publicacion, nickname\n" +
                "FROM fichero_comentario\n" +
                "WHERE nickname = ?\n" +
                "ORDER BY fecha_publicacion DESC\n" +
                "LIMIT 3";

        try {
            ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nickname);
            ps.setString(2, nickname);
            rs = ps.executeQuery();
            Fichero fichero;
            ArrayList<Fichero> listaFicheros = new ArrayList<>();
            Timestamp timestamp;

            while (rs.next()) {
                fichero = new Fichero(rs.getInt("id_fichero"));
                fichero.setNombre(rs.getString("nombre"));
                fichero.setTipo(rs.getString("tipo"));
                timestamp = rs.getTimestamp("fecha_publicacion");
                fichero.setFecha_publicacion(timestamp.toLocalDateTime());
                fichero.setNickname(rs.getString("nickname"));
                
                if (rs.getString("tabla_fuente").equals("fichero_foro")){
                    fichero.setId_foro(rs.getInt("id_foro"));
                }
                else {
                    fichero.setId_comentario(rs.getInt("id_foro"));
                }

                
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
    
    public static ArrayList<Fichero> getFicherosUser(String nickname) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query;

        query = "SELECT 'fichero_foro' AS tabla_fuente, id_fichero, id_foro, nombre, tipo, file, fecha_publicacion, nickname\n" +
                "FROM fichero_foro\n" +
                "WHERE nickname = ?\n" +
                "UNION ALL\n" +
                "SELECT 'fichero_comentario' AS tabla_fuente, id_fichero, id_comentario, nombre, tipo, file, fecha_publicacion, nickname\n" +
                "FROM fichero_comentario\n" +
                "WHERE nickname = ?\n" +
                "ORDER BY fecha_publicacion DESC";

        try {
            ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nickname);
            ps.setString(2, nickname);
            rs = ps.executeQuery();
            Fichero fichero;
            ArrayList<Fichero> listaFicheros = new ArrayList<>();
            Timestamp timestamp;

            while (rs.next()) {
                fichero = new Fichero(rs.getInt("id_fichero"));
                fichero.setId_fichero(rs.getInt("id_fichero"));
                fichero.setNombre(rs.getString("nombre"));
                fichero.setTipo(rs.getString("tipo"));
                timestamp = rs.getTimestamp("fecha_publicacion");
                fichero.setFecha_publicacion(timestamp.toLocalDateTime());
                fichero.setNickname(rs.getString("nickname"));
                
                if (rs.getString("tabla_fuente").equals("fichero_foro")){
                    fichero.setId_foro(rs.getInt("id_foro"));
                }
                else {
                    fichero.setId_comentario(rs.getInt("id_foro"));
                }

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
    
    /**
     * Devuelve el fichero deseado. Puede ser tanto un fichero subido a un comentario como uno de un foro
     * @param id_fichero
     * @return
     * @throws SQLException 
     */
    public static byte[] getFichero(String id_fichero, int id_comentario) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;
        
        if (id_comentario!=-1){
            query = "SELECT * FROM fichero_comentario WHERE id_fichero=?";
        }
        else {
            query = "SELECT * FROM fichero_foro WHERE id_fichero=?";
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id_fichero);
            rs = ps.executeQuery();

            if (rs.next()) { 
                Blob blob = rs.getBlob("file"); 

                // Read data from Blob into a byte array output stream
                try (InputStream in = blob.getBinaryStream()) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            pool.freeConnection(connection);
        }

        return outputStream.toByteArray();
    }
    
    public static String getNombreFichero(String id_fichero, int id_comentario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query;
        String nombre = null;
        
        if (id_comentario!=-1){
            query = "SELECT nombre FROM fichero_comentario WHERE id_fichero=?";
        }
        else {
            query = "SELECT nombre FROM fichero_foro WHERE id_fichero=?";
        }

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id_fichero);
            rs = ps.executeQuery();
            if (rs.next()) nombre = rs.getString("nombre");
            rs.close();
            ps.close();
            pool.freeConnection(connection);

            return nombre;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static int[] uploadFile(Fichero file, InputStream inputStream) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query;
        Timestamp timestamp = new Timestamp(new Date().getTime());
        int[] resultado={-1,-1};
        
        if (file.getId_foro() != -1) {
            query = "INSERT INTO fichero_foro (id_foro, nombre, tipo, fecha_publicacion, nickname, file) VALUES ("+file.getId_foro()+",?, ?, ?, ?, ?)";
        } else {
            query = "INSERT INTO fichero_comentario (id_comentario, nombre, tipo, fecha_publicacion, nickname, file) VALUES ("+file.getId_comentario()+",?, ?, ?, ?, ?)";
        }

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, file.getNombre());
            ps.setString(2, file.getTipo());
            ps.setTimestamp(3, timestamp);
            ps.setString(4, file.getNickname());

            if (inputStream != null) {
                ps.setBlob(5, inputStream);
            }

            resultado[0] = ps.executeUpdate();

            ps.close();
            pool.freeConnection(connection);

            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return resultado;
        }
    }
    
    public static void deleteFichero(int id_fichero,int id_comentario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query;

        if (id_comentario!=-1){
            query = "DELETE FROM fichero_comentario f WHERE f.id_fichero= ?";
        }
        else {
            query = "DELETE FROM fichero_foro f WHERE f.id_fichero= ?";
        }
        
            
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id_fichero);
            ps.executeUpdate();

            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isOwner(int id_fichero, String nickname, int id_comentario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query;
        
        if (id_comentario!=-1){
            query = "SELECT * FROM fichero_comentario WHERE id_fichero=? AND nickname=?";
        }
        else {
            query = "SELECT * FROM fichero_foro WHERE id_fichero=? AND nickname=?";
        }
        
        
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id_fichero);
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
    
    public static int getComentarioAsociado(int idFichero) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT id_comentario FROM fichero_comentario WHERE id_fichero = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, idFichero);
            rs = ps.executeQuery();

            // Si se encuentra el fichero en la tabla fichero_relacion, significa que está asociado a un comentario
            if (rs.next()) {
                return rs.getInt("id_comentario");
            } else {
                return -1; // Si no se encuentra asociado a un comentario, devuelve -1
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                pool.freeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static int getForoAsociado(int idFichero) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT id_foro FROM fichero_foro WHERE id_fichero = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, idFichero);
            rs = ps.executeQuery();

            // Si se encuentra el fichero en la tabla fichero_relacion, significa que está asociado a un foro
            if (rs.next()) {
                return rs.getInt("id_foro");
            } else {
                return -1; // Si no se encuentra asociado a un foro, devuelve -1
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                pool.freeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static int getFicheroAsociado(int idComentario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT id_fichero FROM fichero_comentario WHERE id_comentario = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, idComentario);
            rs = ps.executeQuery();

            // Si se encuentra el fichero en la tabla fichero_relacion, significa que está asociado a un comentario
            if (rs.next()) {
                return rs.getInt("id_fichero");
            } else {
                return -1; // Si no se encuentra asociado a un comentario, devuelve -1
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                pool.freeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static Fichero setId_relacion(Fichero fichero, ResultSet rs) throws SQLException{
        try {
            fichero.setId_foro(rs.getInt("id_foro"));
        }
        catch (SQLException e){
            fichero.setId_comentario(rs.getInt("id_comentario"));
        }
        return fichero;
    }
}