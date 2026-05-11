package course_manager_v1.main;

import course_manager_v1.controller.AuthController;
import course_manager_v1.model.User;
import course_manager_v1.util.ControllerProvider;

import java.util.Scanner;


public class MainClass {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthController authController = new AuthController(sc);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Course Management System ---");
            System.out.println("1. Register");
            System.out.println("2. Log In");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            switch (sc.nextLine().trim()) {
                case "1":
                    handleSession(authController.signup(), sc);
                    break;
                case "2":
                    handleSession(authController.login(), sc);
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        sc.close();
    }

    private static void handleSession(User user, Scanner sc) {
        if (user != null) {
            ControllerProvider.getUserController(user).showMenu(user, sc);
        }
    }
}