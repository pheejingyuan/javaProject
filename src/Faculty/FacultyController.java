package Faculty;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import SQLConnector.MysqlCon;
import Student.Student;

public class FacultyController {
    private Connection connect;

    public FacultyController() {
        connect = MysqlCon.getConnection();
    }

    public boolean updateParticulars(Faculty currentUser, Faculty faculty) {
        boolean result = false;
        try {
            Statement stmt = connect.createStatement();
            String query = String.format("update user set password = '%s', phoneNumber = '%s' where userID = %d;",
                    faculty.getPassword(), faculty.getPhoneNumber(), currentUser.getFacultyID());
            boolean outcome = stmt.execute(query);
            int outcome2 = stmt.getUpdateCount();
            if (outcome2 == -1) {
                result = false;
            } else {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("Faculty Update Particulars");
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean removeStudent(int sID) {
        boolean result = false;
        try {
            Statement stmt = connect.createStatement();
            String query = String.format("delete from user where userID = %d;", sID);
            boolean outcome = stmt.execute(query);
            int outcome2 = stmt.getUpdateCount();
            if (outcome2 == -1) {
                result = false;
            } else {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("Faculty removeStudent");
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean addStudent(Student student) {
        boolean result = false;
        try {
            Statement stmt = connect.createStatement();
            String query = String.format("insert into user values(%d, '%s','%s', '%s', 'Student');",
                    student.getStudentID(), student.getName(), student.getPassword(), student.getPhoneNumber());
            boolean outcome = stmt.execute(query);
            int outcome2 = stmt.getUpdateCount();
            if (outcome2 == -1) {
                result = false;
            } else {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("Faculty addStudent");
            System.out.println(e.getMessage());
        }
        return result;
    }
}