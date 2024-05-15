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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import studyhub.business.Asignatura;

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

        query = "SELECT * FROM tema WHERE id_foro=? ORDER BY fecha_publicacion DESC";

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

        query = "SELECT * FROM tema WHERE id_tema=?";

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

        query = "SELECT * FROM tema WHERE id_foro=? ORDER BY fecha_publicacion DESC LIMIT ?";

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
    
      public static ArrayList<Tema> getTodosTemas() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;

        query = "SELECT * FROM tema";

        try {
            ps = connection.prepareStatement(query);
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

        query = "SELECT t.*, COUNT(c.id_tema) as num_referencias FROM tema t LEFT JOIN comentario c ON c.id_tema=t.id_tema WHERE id_foro= ? GROUP BY t.id_tema ORDER BY num_referencias DESC LIMIT ?";

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
     
    public static void deleteTema(int id_tema) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query;

        query = "DELETE FROM tema t WHERE t.id_tema= ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id_tema);
            ps.executeUpdate();

            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Metodo que introduce un nuevo tema en la base de datos si este no ha sido creado previamente
     * 
     * 
     * @param titulo titulo del tema
     * @param descripcion descripcion del tema
     * @param nicknameUsuario nombre del usuario que ha creado el tema
     * @param idForo identificador del foro donde se ha creado el tema
     */
    public static int crearTema(String titulo, String descripcion, String nicknameUsuario, int idForo){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query = "INSERT INTO tema (titulo, descripcion, fecha_publicacion, likes, dislikes, nickname, id_foro)  VALUES (?,?,?,?,?,?,?)";
        int idNuevaFila=-1;
        int filasCreadas;
        
        try { 
            if(!titulo.equals("") && nicknameUsuario != null 
                    && !nicknameUsuario.equals("") && descripcion != null && !descripcion.equals("")){
              
                Timestamp timestamp = new Timestamp(new Date().getTime());
                int numLikes = 0; // numLikes = numDislikes = 0 en cuanto se crea el tema
                ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, titulo);
                ps.setString(2, descripcion);
                ps.setTimestamp(3, timestamp);
                ps.setInt(4, numLikes); //likes
                ps.setInt(5, numLikes); //dislikes
                ps.setString(6, nicknameUsuario);
                ps.setInt(7, idForo);
                
                filasCreadas=ps.executeUpdate();
                
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idNuevaFila = generatedKeys.getInt(1);
                }
                
                ps.close();
             }

            
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        
        return idNuevaFila;
    }
    
        /**
         * Metodo que busca en todos los temas de la base de datos a ver si un tema esta creado previamente
         * 
         * @param idTema identificador del tema
         * @param idForo identificador del foro
         * @return true si el tema ya estaba creado. False en caso contrario
         */
    public static boolean estaTemaNoCreado(int idTema, int idForo) {
            ArrayList<Tema> temas = getTemas(Integer.toString(idForo));
            for(Tema tema : temas){
                if(idTema == tema.getId_tema())
                    return true;
            }
            return false;
    }
    
    
    public static int getSiguienteId() {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String query;

            query = "SELECT T.idTema FROM tema T where T.idTema = MAX(SELECT T.idTema FROM tema T)";

            try {
                ps = connection.prepareStatement(query);
                rs = ps.executeQuery();
                
                int id = ((Number) rs.getObject(1)).intValue();
                id = id+1;
                
                rs.close();
                ps.close();
                pool.freeConnection(connection);
                
                return id;
                
            }
            catch(SQLException e){
                e.printStackTrace();
                return -1;
            }
    }
    
    public static ArrayList<Tema> buscarTema(String busqueda) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;
        
        query = "SELECT * FROM tema WHERE titulo LIKE ? ORDER BY fecha_publicacion DESC";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%"+busqueda+"%");
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
