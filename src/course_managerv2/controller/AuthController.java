package course_managerv2.controller;

import course_managerv2.model.User;
import course_managerv2.service.AuthService;
import course_managerv2.util.Validator;

import java.util.Scanner;

public class AuthController {
    private final Scanner sc = new Scanner(System.in);
    private final AuthService authService = new AuthService();

    public User signup() {
        String name;
        while (true) {
            System.out.print("Enter Name : ");
            name = sc.nextLine().trim();
            if (Validator.isValidName(name)) {
                break;
            }
            System.out.println("Name must be at least 3 characters. Try again");
        }

        String email;
        while (true) {
            System.out.print("Enter Email : ");
            email = sc.nextLine().trim();
            if(!Validator.isValidEmail(email)){
                System.out.println("Invalid email format. Try again. ");
                continue;
            }
            if(authService.isUserExists(email)){
                System.out.println("User already exists. Try login.");
                return null;
            }
            break;
        }

        String password ;
        while(true){
            System.out.print("Password : ");
            password = sc.nextLine().trim();
            if(Validator.isValidPassword(password)){
                break;
            }
            System.out.println("Password must be at least 6 characters. ");
        }

        int choice;
        while (true){
            System.out.println("\nChoose Role: ");
            System.out.println("1. Student");
            System.out.println("2. Instructor");

            System.out.print("Enter choice: ");
            String choiceStr = sc.nextLine().trim();
            if(choiceStr.equalsIgnoreCase("1") || choiceStr.equalsIgnoreCase("2")){
                choice = Integer.parseInt(choiceStr);
                break;
            }
            System.out.println("Invalid choice. Please enter 1 or 2");
        }

        User user = authService.signup(name, email,password, choice);

        System.out.println("Signup successful! Welcome "+ user.getName());
        return user;

    }

    public User login() {
        String email;
        while (true) {
            System.out.print("Enter Email : ");
            email = sc.nextLine().trim();

            if (Validator.isValidEmail(email)) break;
            System.out.println("Invalid email format.");
        }

        String password;
        while (true) {
            System.out.print("Enter Password : ");
            password = sc.nextLine().trim();

            if (Validator.isValidPassword(password)) break;
            System.out.println("Invalid password format.");
        }

        User user = authService.login(email, password);

        if(user == null){
            System.out.println("Login failed. Check credentials.");
            return null;
        }

        System.out.println("\nWelcome back, " + user.getName() );
        return user;
    }

}