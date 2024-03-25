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
        
        query = "SELECT * FROM FORO";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Asignatura asignatura = null;
            ArrayList<Asignatura> listaAsignaturas=new ArrayList<>();
            
            while (rs.next()) {
                asignatura = new Asignatura();
                asignatura.setNombre("nombre");
                asignatura.setCurso("curso");
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
}