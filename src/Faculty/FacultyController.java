package Faculty;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import SQLConnector.MysqlCon;

public class FacultyController {
    private Connection connect;

    public FacultyController() {
        connect = MysqlCon.getConnection();
    }
}