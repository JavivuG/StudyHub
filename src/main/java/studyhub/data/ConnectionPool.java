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
            InitialContext initialContext = new InitialContext();
            Context context = (Context) initialContext.lookup("java:comp/env");
            //The JDBC Data source that we just created
            this.dataSource = (DataSource) context.lookup("studyhub");
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