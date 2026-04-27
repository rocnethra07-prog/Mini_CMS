package course_manager.main;

import course_manager.controller.AuthController;
import course_manager.controller.UserController;
import course_manager.model.User;
import course_manager.repository.ControllerRegistry;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        AuthController authController = new AuthController();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while(running){
            System.out.println("\n--- Course Management System ---");
            System.out.println("1. Register");
            System.out.println("2. Log In");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            switch(sc.nextLine().trim()){
                case "1":
                    User newUser = authController.signup();
                    if(newUser != null){
                        UserController controller = ControllerRegistry.getController(newUser);
                        controller.showMenu(newUser);
                    }
                    break;
                case "2":
                    User user = authController.login();
                    if(user != null){
                        UserController controller = ControllerRegistry.getController(user);
                        controller.showMenu(user);
                    }
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
