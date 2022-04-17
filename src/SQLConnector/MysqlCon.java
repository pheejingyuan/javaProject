package SQLConnector;

import java.sql.*;

public class MysqlCon {

    static Connection conn = null;

    public static Connection getConnection() {
        if (conn != null)
            return conn;
        String database = "test";
        String Username = "root";
        String password = "password";
        return getConnection(database, Username, password);
    }

    private static Connection getConnection(String databaseName, String UserName, String password) {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + databaseName + "?user=" + UserName + "&password=" + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}