import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

import Student.*;
import User.User;
import Faculty.*;
import SQLConnector.MysqlCon;
import Admin.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        StudentView studentView = new StudentView();
        FacultyView facultyView = new FacultyView();
        AdminView adminView = new AdminView();
        // Implementing the login first.
        User currentUser = mainMenu2();
        if (currentUser == null) {
            System.out.print("Please contact your admin for further instructions");
        }
        char option = extracted2(currentUser);
        int internalOption = 0;
        switch (option) {
            case 'S':
                while (internalOption != -1) {
                    studentMenu();
                    internalOption = sc.nextInt();
                    sc.nextLine();
                    switch (internalOption) {
                        case 1:
                            System.out.print("Enter new phone number: ");
                            String newPhoneNumber = sc.nextLine();
                            System.out.print("Enter new password: ");
                            String newPassword = sc.nextLine();
                            Student tempStudent = new Student(newPhoneNumber, newPassword);
                            // Either or for the 2 lines below.
                            boolean result = studentView.updateParticulars((Student) currentUser, tempStudent);
                            if (result) {
                                System.out.println("Successfully updated particulars");
                            } else {
                                System.out.println("Error");
                            }
                            // studentView.updateParticulars((Student) currentUser, new
                            // Student(newPhoneNumber, newPassword));
                            System.out.println();
                            break;
                        case 2:
                            studentView.viewCourses((Student) currentUser);
                            System.out.println();
                            break;
                        case 3:
                            studentView.registerCourses((Student) currentUser);
                            System.out.println();
                            break;
                        case 4:
                            internalOption = -1;
                            break;
                    }
                }
                break;
            case 'F':
                /*
                 * System.out.println("1. Update particulars");
                 * System.out.println("2. Manage Students");
                 * System.out.println("3. Manage Assessments");
                 * System.out.println("4. Manage Courses");
                 * System.out.println("5. Back");
                 * System.out.print("Enter a number:");
                 */
                while (internalOption != -1) {
                    facultyMenu();
                    internalOption = sc.nextInt();
                    sc.nextLine();
                    switch (internalOption) {
                        case 1:
                            String[] particulars = getParticulars();
                            if (facultyView.updateParticulars((Faculty) currentUser,
                                    new Faculty(particulars[1], particulars[0]))) {
                                System.out.println("Particulars has been updated");
                            } else {
                                System.out.println("Particulars has not been updated. ");
                            }
                            // this will only allow them to make changes to their password and phone number
                            break;
                        case 2:
                            System.out.println("Enter Student ID: ");
                            int SID = sc.nextInt();
                            sc.nextLine();
                            if (studentView.getStudentByID(SID)) {
                                System.out.println("Do you want to remove this student?");
                                if (sc.nextLine().equals("Y")) {
                                    facultyView.removeStudent(SID);
                                    System.out.println("Student Removed");
                                } else {
                                    System.out.println("Error");
                                }
                            } else {
                                // prompt the user for student particulars, name, phoneNumber, password
                                System.out.println("Enter Student Name: ");
                                String sname = sc.nextLine();
                                System.out.println("Enter Student phone number: ");
                                String sHP = sc.nextLine();
                                System.out.println("Enter Student password: ");
                                String sPassword = sc.nextLine();
                                facultyView.addStudent(new Student(SID, sname, sHP, sPassword));
                            }
                            break;
                        case 3:
                            facultyView.manageAssessments();
                            break;
                        case 4:
                            facultyView.manageCourses();
                            break;
                        case 5:
                            // mainMenu();
                            // option = sc.nextInt();
                            break;
                    }
                }
                break;
            case 'A':
                while (internalOption != -1) {
                    adminMenu();
                    internalOption = sc.nextInt();
                    sc.nextLine();
                    switch (internalOption) {
                        case 1:
                            adminView.manageFaculty();
                            break;
                        case 2:
                            // mainMenu();
                            // option = sc.nextInt();
                            break;
                    }
                }
                break;
        }
    }

    private static String[] getParticulars() {
        System.out.print("Enter your new password:");
        String newPassword = sc.nextLine();
        System.out.print("Enter your new phone number:");
        String newPhoneNumber = sc.nextLine();
        return new String[] { newPassword, newPhoneNumber };
    }

    private static char extracted2(User currentUser) {
        if (currentUser instanceof Student) {
            System.out.println("Student"); // Logging
            System.out.println((Student) currentUser);
            return 'S';
        } else if (currentUser instanceof Admin) {
            System.out.println("Admin"); // Logging
            return 'A';
        } else if (currentUser instanceof Faculty) {
            System.out.println("Faculty"); // Logging
            return 'F';
        }
        return 'Z';
    }

    private static User login(int username, String password) {
        Connection conn = MysqlCon.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String query = String.format("select * from user where userID = %d and password = '%s'", username,
                    password);
            System.out.println(query); // Logging Method
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                if (result.getString(5).equals("Student")) {
                    return new Student(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));
                } else if (result.getString(5).equals("Admin")) {
                    return new Admin(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));
                } else if (result.getString(5).equals("Faculty")) {
                    return new Faculty(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Login Error");
        }
        return null;
    }

    // public static void mainMenu() {
    // System.out.println("1. Student");
    // System.out.println("2. Faculty");
    // System.out.println("3. Admin");
    // System.out.println("0. Quit");
    // System.out.print("Enter a number:");
    // }

    public static User mainMenu2() {
        System.out.print("Enter your username: ");
        int username = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();
        return login(username, password); // != null ? login(username, password) : null; // ternary operation
        /*
         * if(login(username, password ) != null){
         * return login(username, password)
         * }else{
         * return null
         * }
         */
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