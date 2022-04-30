package Course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import SQLConnector.MysqlCon;
import Student.Student;

public class CourseController {
    private Connection connect;

    public CourseController() {
        connect = MysqlCon.getConnection();
    }

    public List<Course> getAllCoursesNotTaken(int sid) {
        ArrayList<Course> tempArrayList = new ArrayList<>();
        try {
            Statement stmt = connect.createStatement();
            String query = String
                    .format("select * from course where cid not in (select cid from registration where sid = %d)", sid);
            System.out.println(query);
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                tempArrayList.add(new Course(result.getString(1), result.getString(2)));
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("getAllCoursesNotTaken");
            System.out.println(e.getMessage());
        }
        return tempArrayList;
    }

    public boolean addCourse(Student currentUser, String courseID) {
        try {
            System.out.println("Add Course param " + currentUser + " Course ID is " + courseID);
            Statement stmt = connect.createStatement();
            String query = String.format("insert into registration values(%d, '%s', 'Sem 1')", currentUser.getStudentID(),
                    courseID);
            boolean result = stmt.execute(query);
            if (stmt.getUpdateCount() == -1) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("addCourse");
            System.out.println(e.getMessage());
        }
        return false;
    }
}
