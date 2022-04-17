package Student;

import java.util.List;

import Course.Course;

public class StudentView {
    private StudentController studentController;

    public StudentView() {
        studentController = new StudentController();
    }

    public boolean updateParticulars(Student oldStudent, Student newStudent) {
        return false;
    }

    public void viewCourses(Student student) {
        List<String> result = studentController.viewCourses(student);
        for (String courseID : result) {
            System.out.println("Course ID is " + courseID);
        }
    }

    public void registerCourses(Course course) {

    }
}
