package course_manager_v2.service;

import course_manager_v2.model.Instructor;
import course_manager_v2.model.Role;
import course_manager_v2.model.Student;
import course_manager_v2.model.User;
import course_manager_v2.repository.UserRepo;


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

    public User signup(String name, String email, String password, Role role) {

        User user = createUser(name, email,role);
        userRepo.saveUser(user, password);

        return user;
    }


    private User createUser(String name, String email, Role role){
        User user = null;
        switch (role){
            case STUDENT -> user = new Student(name,email);
            case INSTRUCTOR -> user = new Instructor(name, email);
            default -> throw new IllegalArgumentException("Invalid Role");
        }
        return user;
    }
}
