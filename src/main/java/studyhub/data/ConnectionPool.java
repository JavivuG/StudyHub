package studyhub.data;

import java.sql.*;
import javax.naming.Context;
import javax.sql.DataSource;
import javax.naming.InitialContext;
public class ConnectionPool {
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    private ConnectionPool() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            InitialContext ic = new InitialContext();
dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/studyhub");
        } catch(Exception e) {
            System.err.println("Error al buscar el DataSource: " + e.getMessage());
        }
    }


    public static ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
            c.close();
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}