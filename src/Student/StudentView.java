package Student;

import java.util.List;
import java.util.Scanner;

import Course.Course;
import Course.CourseController;

public class StudentView {
    private StudentController studentController;
    private CourseController courseController;

    public StudentView() {
        studentController = new StudentController();
        courseController = new CourseController();
    }

    // Only allow them to update phone number and password
    public boolean updateParticulars(Student oldStudent, Student newStudent) {
        return studentController.updateStudent(oldStudent, newStudent);
    }

    // public boolean updateParticulars(Student oldStudent, String newPhoenNumber,
    // String newPassword) {
    // return false;
    // }
    public boolean getStudentByID(int id) {
        return studentController.getStudentByID(id) == null ? false : true;
    }

    public void viewCourses(Student student) {
        List<String> result = studentController.viewCourses(student);
        int index = 1;
        for (String courseID : result) {
            System.out.println(index++ + ". Course ID is " + courseID);
        }
    }

    public void registerCourses(Student currentUser) {
        List<Course> coursesUserHaveNotTaken = courseController.getAllCoursesNotTaken(currentUser.getStudentID());
        System.out.println("You have not taken these courses.");
        for (Course c : coursesUserHaveNotTaken) {
            System.out.println(c.getCid());
        }
        System.out.print("Please enter the course ID: ");
        Scanner sc = new Scanner(System.in);
        String courseID = sc.nextLine();
        System.out.println(courseID);
        boolean isAdded = courseController.addCourse(currentUser, courseID);
        if (!isAdded) {
            System.out.println("Error in register course");
        } else {
            System.out.println("Successfully register course");
        }
    }
}
