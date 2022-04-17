import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Student.*;
import Faculty.*;
import SQLConnector.MysqlCon;
import Admin.*;

public class Main {

    public static void main(String[] args) {
        StudentView studentView = new StudentView();
        FacultyView facultyView = new FacultyView();
        AdminView adminView = new AdminView();
        // Implementing the login first.
        Scanner sc = new Scanner(System.in);
        mainMenu();
        int option = sc.nextInt();
        int internalOption = 0;
        sc.nextLine();
        while (option != 0) {
            switch (option) {
                case 1:
                    studentMenu();
                    internalOption = sc.nextInt();
                    sc.nextLine();
                    switch (internalOption) {
                        case 1:
                            // studentView.updateParticulars("oldStudent", "newStudent");
                            break;
                        case 2:
                            studentView.viewCourses(new Student(100, "password", "name", "phoneNumber"));
                            break;
                        case 3:
                            // studentView.registerCourses("course");
                            break;
                        case 4:
                            mainMenu();
                            option = sc.nextInt();
                            break;
                    }
                    break;
                case 2:
                    facultyMenu();
                    /*
                     * System.out.println("1. Update particulars");
                     * System.out.println("2. Manage Students");
                     * System.out.println("3. Manage Assessments");
                     * System.out.println("4. Manage Courses");
                     * System.out.println("5. Back");
                     * System.out.print("Enter a number:");
                     */
                    internalOption = sc.nextInt();
                    sc.nextLine();
                    switch (internalOption) {
                        case 1:
                            facultyView.updateParticulars();
                            break;
                        case 2:
                            facultyView.manageStudents();
                            break;
                        case 3:
                            facultyView.manageAssessments();
                            break;
                        case 4:
                            facultyView.manageCourses();
                            break;
                        case 5:
                            mainMenu();
                            option = sc.nextInt();
                            break;
                    }
                    break;
                case 3:
                    adminMenu();
                    internalOption = sc.nextInt();
                    sc.nextLine();
                    switch (internalOption) {
                        case 1:
                            adminView.manageFaculty();
                            break;
                        case 2:
                            mainMenu();
                            option = sc.nextInt();
                            break;
                    }
                    break;
            }
        }

    }

    private static boolean login(int username, String password) {
        Connection conn = MysqlCon.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String query = String.format("select * from user where userID = %d and password = '%s'", username,
                    password);
            System.out.println(query);
            ResultSet result = stmt.executeQuery(query);
            int count = 0;
            while (result.next()) {
                count++;
            }
            if (count == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Login Error");
        }
        return false;

    }

    public static void mainMenu() {
        System.out.println("1. Student");
        System.out.println("2. Faculty");
        System.out.println("3. Admin");
        System.out.println("0. Quit");
        System.out.print("Enter a number:");

    }

    public static void studentMenu() {
        System.out.println("1. Update particulars");
        System.out.println("2. View Registered Courses");
        System.out.println("3. Register Course");
        System.out.println("4. Back");
        System.out.print("Enter a number:");
    }

    public static void facultyMenu() {
        System.out.println("1. Update particulars");
        System.out.println("2. Manage Students");
        System.out.println("3. Manage Assessments");
        System.out.println("4. Manage Courses");
        System.out.println("5. Back");
        System.out.print("Enter a number:");
    }

    public static void adminMenu() {
        System.out.println("1. Manage Faculty");
        System.out.println("2. Back");
        System.out.print("Enter a number:");
    }

}