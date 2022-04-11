import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
                    switch (internalOption) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                            mainMenu();
                            option = sc.nextInt();
                            break;
                    }
                    sc.nextLine();
                    break;
                case 2:
                    facultyMenu();
                    internalOption = sc.nextInt();
                    switch (internalOption) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            mainMenu();
                            option = sc.nextInt();
                            break;
                    }
                    sc.nextLine();
                    break;
                case 3:
                    adminMenu();
                    internalOption = sc.nextInt();
                    switch (internalOption) {
                        case 1:
                        case 2:
                            mainMenu();
                            option = sc.nextInt();
                            break;
                    }
                    sc.nextLine();
                    break;
            }
        }

    }

    public static void mainMenu() {
        System.out.println("Enter a number:");
        System.out.println("1. Student");
        System.out.println("2. Faculty");
        System.out.println("3. Admin");
        System.out.println("0. Quit");
    }

    public static void studentMenu() {
        System.out.println("Enter a number:");
        System.out.println("1. Update particulars");
        System.out.println("2. View Registered Courses");
        System.out.println("3. Register Course");
        System.out.println("4. Back");
    }

    public static void facultyMenu() {
        System.out.println("Enter a number:");
        System.out.println("1. Update particulars");
        System.out.println("2. Manage Students");
        System.out.println("3. Manage Assessments");
        System.out.println("4. Manage Courses");
        System.out.println("5. Back");
    }

    public static void adminMenu() {
        System.out.println("Enter a number:");
        System.out.println("1. Manage Faculty");
        System.out.println("2. Back");
    }

}