package studyhub.data;

import studyhub.business.Asignatura;
import java.util.ArrayList;
import java.sql.*;

public class ForoDB {

    public static ArrayList<Asignatura> getAsignaturas() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;
        
        query = "SELECT * FROM foro";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Asignatura asignatura = null;
            ArrayList<Asignatura> listaAsignaturas=new ArrayList<>();
            
            while (rs.next()) {
                asignatura = new Asignatura();
                asignatura.setID_asignatura(rs.getInt("id_foro"));
                asignatura.setNombre(rs.getString("nombre"));
                asignatura.setCurso(rs.getString("curso"));
                listaAsignaturas.add(asignatura);
            }
            
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return listaAsignaturas;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static ArrayList<Asignatura> getAsignaturas(int numero_max_asignaturas) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;
        
        query = "SELECT * FROM foro LIMIT ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, numero_max_asignaturas);
            rs = ps.executeQuery();
            Asignatura asignatura = null;
            ArrayList<Asignatura> listaAsignaturas=new ArrayList<>();
            
            while (rs.next()) {
                asignatura = new Asignatura();
                asignatura.setID_asignatura(rs.getInt("id_foro"));
                asignatura.setNombre(rs.getString("nombre"));
                asignatura.setCurso(rs.getString("curso"));
                listaAsignaturas.add(asignatura);
            }
            
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return listaAsignaturas;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public static Asignatura getAsignatura(String id_foro) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;
        
        query = "SELECT * FROM foro WHERE id_foro= ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id_foro);
            rs = ps.executeQuery();
            Asignatura asignatura = null;
            
            if (rs.next()) {
                asignatura = new Asignatura();
                asignatura.setID_asignatura(rs.getInt("id_foro"));
                asignatura.setNombre(rs.getString("nombre"));
                asignatura.setCurso(rs.getString("curso"));
            }
            
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return asignatura;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
        public static boolean asignaturaExists(String nombre) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM foro WHERE nombre = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombre);
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
        
    public static int crearAsignatura(String nombreAsignatura, String curso) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query="INSERT INTO foro (nombre,curso) VALUES (?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombreAsignatura);
            ps.setString(2, curso);

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