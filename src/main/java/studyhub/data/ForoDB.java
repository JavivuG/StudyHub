package studyhub.data;

import studyhub.business.Asignatura;
import java.util.ArrayList;
import java.sql.*;
import java.util.Comparator;
import studyhub.business.ContribucionAsignatura;

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

    public static int deleteAsignatura(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "DELETE FROM foro WHERE id_foro = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static ArrayList<Asignatura> buscarAsignatura(String busqueda) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;
        
        query = "SELECT * FROM foro WHERE nombre LIKE ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%"+busqueda+"%");
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
    
    public static ArrayList<ContribucionAsignatura> getContribucionAsignaturas(String nickname) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ContribucionAsignatura> lista_contribucion=new ArrayList<>();
        
        String query = "SELECT\n" +
            "f.id_foro,\n" +
            "f.nombre,\n" +
            "f.curso,\n" +
            "(SELECT COUNT(id_fichero) FROM fichero_foro ff WHERE ff.id_foro = f.id_foro AND nickname=?) +\n" +
            "(SELECT COUNT(id_tema) FROM tema t WHERE t.id_foro = f.id_foro AND nickname=?) +\n" +
            "(SELECT COUNT(id_comentario) FROM comentario c JOIN tema t ON c.id_tema = t.id_tema WHERE t.id_foro = f.id_foro AND c.nickname = ?) AS contribuciones_usuario,\n" +
            "(SELECT COUNT(id_fichero) FROM fichero_foro ff WHERE ff.id_foro = f.id_foro) +\n" +
            "(SELECT COUNT(id_tema) FROM tema t WHERE t.id_foro = f.id_foro) +\n" +
            "(SELECT COUNT(id_comentario) FROM comentario c JOIN tema t ON c.id_tema = t.id_tema WHERE t.id_foro = f.id_foro) AS total_contribuciones\n" +
            "FROM foro f\n" +
            "GROUP BY f.id_foro\n" +
            "LIMIT 4";

        try {
            ps = connection.prepareStatement(query);
                    
            ps.setString(1, nickname);
            ps.setString(2, nickname);
            ps.setString(3, nickname);
            
            rs = ps.executeQuery();
            while(rs.next()){
                Asignatura asignatura=new Asignatura();
                ContribucionAsignatura contr=new ContribucionAsignatura();

                asignatura.setID_asignatura(rs.getInt("id_foro"));
                asignatura.setNombre(rs.getString("nombre"));
                asignatura.setCurso(rs.getString("curso"));
                
                contr.setForo(asignatura);
                contr.setContr_usuario(rs.getInt("contribuciones_usuario"));
                contr.setContr_total(rs.getInt("total_contribuciones"));
                lista_contribucion.add(contr);
            }
            
            rs.close();
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        return lista_contribucion;

    }
}