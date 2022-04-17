package Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Course.Course;
import SQLConnector.MysqlCon;

public class StudentController {
    private Connection connect;

    public StudentController() {
        connect = MysqlCon.getConnection();
    }

    public List<Student> getAllStudents() {
        ArrayList<Student> tempArrayList = new ArrayList<>();
        try {
            Statement stmt = connect.createStatement();
            String query = "select * from user where role = 'Student';";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Student temp = new Student(rs.getInt(1), rs.getString("password"), rs.getString(3), rs.getString(4));
                tempArrayList.add(temp);
            }
        } catch (SQLException e) {
            System.out.println("Error on getAllStudents");
            System.out.println(e.getMessage());
        }
        return tempArrayList;
    }

    public Student getStudentByName(String name) {
        Student temp = null;
        try {
            Statement stmt = connect.createStatement();
            String query = "select * from user where role = 'Student' and name = " + "'" + name + "';";
            // select * from user where role = 'Student' and name = 'name'
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                temp = new Student(rs.getInt(1), rs.getString("password"), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("Error on getStudentByName");
            System.out.println(e.getMessage());
        }
        return temp;
    }

    public boolean addStudent(Student student) {
        boolean result = false;
        try {
            Statement stmt = connect.createStatement();
            String query = String.format("insert into user values(%d,%s,%s,%s,%s);", student.getStudentID(),
                    student.getPassword(), student.getName(), student.getPhoneNumber(), "Student");
            boolean outcome = stmt.execute(query);
            int outcome2 = stmt.getUpdateCount();
            if (outcome2 == -1) {
                result = false;
            } else {
                result = true;
            }

        } catch (SQLException e) {
            System.out.println("Error on addStudent");
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean updateStudent(Student oldStudent, Student newStudent) {
        // update user set password = %s, name = %s, phoneNumber = %s where userID = %d
        boolean result = false;
        try {
            Statement stmt = connect.createStatement();
            String query = String.format(
                    "update user set password = '%s', name = '%s', phoneNumber = '%s' where userID = %d;",
                    newStudent.getPassword(), newStudent.getName(), newStudent.getPhoneNumber(),
                    oldStudent.getStudentID());
            boolean outcome = stmt.execute(query);
            int outcome2 = stmt.getUpdateCount();
            if (outcome2 == -1) {
                result = false;
            } else {
                result = true;
            }

        } catch (SQLException e) {
            System.out.println("Error on updateStudent");
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<String> viewCourses(Student student) {
        ArrayList<String> temp = new ArrayList<>();
        try {
            Statement stmt = connect.createStatement();
            String query = String.format("select * from registration where sid = %d", student.getStudentID());
            // select * from user where role = 'Student' and name = 'name'
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                temp.add(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Error on getStudentByName");
            System.out.println(e.getMessage());
        }
        return temp;
    }

}