import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import SQLConnector.MysqlCon;

public class Test {
    public static void main(String[] args) {
        Connection connect = MysqlCon.getConnection();
        try {
            Statement stmt = connect.createStatement();

            // ResultSet rs = stmt.executeQuery("select * from student");
            // String query = "insert into user values(103, 'hello' , 'ln',12345,'Student')";
            String selectQuery = "select * from user";
            ResultSet rs = stmt.executeQuery(selectQuery); // select

            // boolean inserted = stmt.execute(query); // create, update, delete
            int rs1 = stmt.getUpdateCount();
            System.out.println("RS1" + rs1);
            // System.out.println(inserted);
            // if (inserted) {
            //     System.out.println("Inserted");
            // } else {
            //     System.out.println("Not Inserted");
            // }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
