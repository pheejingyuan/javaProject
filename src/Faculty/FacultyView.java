package Faculty;

import java.sql.SQLException;

import Student.Student;

public class FacultyView {
    private FacultyController facultyController;

    public FacultyView() {
        facultyController = new FacultyController();
    }

    public void manageCourses() {
    }

    public void manageAssessments() {
    }

    public boolean updateParticulars(Faculty currentUser, Faculty faculty) {
        return facultyController.updateParticulars(currentUser, faculty);
    }

    public boolean removeStudent(int SID) {
        return facultyController.removeStudent(SID);
    }

    public boolean addStudent(Student student) {
        return facultyController.addStudent(student);
    }
}
