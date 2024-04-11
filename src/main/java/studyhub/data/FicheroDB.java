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
import java.util.regex.*;  

/**
 *
 * @author javier
 */
public class FicheroDB {

    public static ArrayList<Fichero> getFicheros(String id_foro) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query;

        query = "SELECT * FROM fichero WHERE id_foro=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id_foro);
            rs = ps.executeQuery();
            Fichero fichero;
            ArrayList<Fichero> listaFicheros = new ArrayList<>();
            Timestamp timestamp;

            while (rs.next()) {
                fichero = new Fichero();
                fichero.setId_fichero(rs.getInt("id_fichero"));
                fichero.setNombre(rs.getString("nombre"));
                fichero.setTipo(rs.getString("tipo"));
                timestamp = rs.getTimestamp("fecha_publicacion");
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

    public static byte[] getFichero(String id_fichero) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;

        query = "SELECT * FROM fichero WHERE id_fichero=?";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id_fichero);
            rs = ps.executeQuery();

            if (rs.next()) { // Assuming only one result is expected for the given id_fichero
                Blob blob = rs.getBlob("file"); // Replace "nombre_del_campo_blob" with the actual column name

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

    public static ArrayList<Fichero> getFicherosRecientes(String id_foro, int max_ficheros) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query;

        query = "SELECT * FROM fichero WHERE id_foro=? ORDER BY fecha_publicacion DESC LIMIT ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id_foro);
            ps.setInt(2, max_ficheros);
            rs = ps.executeQuery();
            Fichero fichero;
            ArrayList<Fichero> listaFicheros = new ArrayList<>();
            Timestamp timestamp;

            while (rs.next()) {
                fichero = new Fichero();
                fichero.setId_fichero(rs.getInt("id_fichero"));
                fichero.setNombre(rs.getString("nombre"));
                fichero.setTipo(rs.getString("tipo"));
                timestamp = rs.getTimestamp("fecha_publicacion");
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

    public static String getNombreFichero(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT nombre FROM fichero  WHERE id_fichero=?";
        String nombre = null;

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
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
    
     public static String getExtension(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT tipo FROM fichero  WHERE id_fichero=?";
        String extension = null;

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) extension = rs.getString("tipo");
            rs.close();
            ps.close();
            pool.freeConnection(connection);

            Pattern p = Pattern.compile("/([a-z]+)");
            Matcher m = p.matcher(extension);
            
            if(m.find()) {
                return m.group(1);
            } else {
                return "";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int uploadFile(Fichero file, InputStream inputStream) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query = "INSERT INTO fichero (nombre, tipo, fecha_publicacion, nickname, id_foro, file)      VALUES (?, ?, ?, ?, ?, ?)";
        Timestamp timestamp = new Timestamp(new Date().getTime());

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, file.getNombre());
            ps.setString(2, file.getTipo());
            ps.setTimestamp(3, timestamp);
            ps.setString(4, file.getNickname());
            ps.setInt(5, file.getId_foro());

            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                ps.setBlob(6, inputStream);
            }

            // sends the statement to the database server
            int res = ps.executeUpdate();

            ps.close();
            pool.freeConnection(connection);

            

            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
