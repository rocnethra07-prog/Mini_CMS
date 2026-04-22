package course_managerv2.service;

import course_managerv2.model.Instructor;
import course_managerv2.model.Student;
import course_managerv2.model.User;
import course_managerv2.repository.UserRepo;


public class AuthService {
    private final UserRepo userRepo = new UserRepo();

    public boolean isUserExists(String email){
        return userRepo.findUserByEmail(email) != null;
    }

    public User login(String email, String password) {
        if(!userRepo.validateCredentials(email , password)){
            return null;
        }
        return userRepo.findUserByEmail(email);
    }

    public User signup(String name, String email, String password, int choice) {

        User user;
        if(choice == 1){
            user = new Student(name, email);
        }
        else{
            user = new Instructor(name, email);
        }
        userRepo.saveUser(user, password);

        return user;
    }
}
