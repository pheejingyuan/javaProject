package Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import SQLConnector.MysqlCon;

public class AdminController {

    private Connection connect;

    public AdminController() {
        connect = MysqlCon.getConnection();
    }

    public void selectStudents() {
        try {
            Statement stmt = connect.createStatement();
            String selectQuery = "select * from user";
            ResultSet rs = stmt.executeQuery(selectQuery);
        } catch (SQLException e) {
            // TODO: handle exception
        }
    }
}