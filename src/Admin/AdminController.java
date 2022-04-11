package Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import SQLConnector.MysqlCon;

public class AdminController {

    private Connection connect;
    public AdminController(){
        connect = MysqlCon.getConnection();
    }
    public static void selectStudents(){
        try {
            String selectQuery = "select * from user";
            ResultSet rs = stmt.executeQuery(selectQuery);
        } catch (SQLException e) {
            //TODO: handle exception
        }
    }
}