package course_manager_v1.main;

import course_manager_v1.controller.AuthController;
import course_manager_v1.model.User;
import course_manager_v1.util.ControllerProvider;

import java.util.Scanner;


public class MainClass {

    public static void main(String[] args) {
        AuthController authController = new AuthController();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        User user = null ;

        while(running){
            System.out.println("\n--- Course Management System ---");
            System.out.println("1. Register");
            System.out.println("2. Log In");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            switch(sc.nextLine().trim()){
                case "1":
                    user = authController.signup();
                    break;
                case "2":
                    user = authController.login();
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            if(user != null){
                ControllerProvider.getUserController(user).showMenu(user);
                user = null;
            }
        }
    }
}