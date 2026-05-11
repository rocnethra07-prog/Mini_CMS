package course_manager_v1.service;

import course_manager_v1.model.Role;
import course_manager_v1.model.User;

import course_manager_v1.repository.UserRepo;
import course_manager_v1.util.UserFactory;


public class AuthService {
    private final UserRepo userRepo = new UserRepo();

    public boolean isUserExists(String email){
        return userRepo.isUserExists(email);
    }

    public User login(String email, String password) {
        if(!userRepo.validateCredentials(email , password)){
            return null;
        }
        return userRepo.findUserByEmail(email);
    }

    public User signup(String name, String email, String password, Role role) {
        if (isUserExists(email)) {
            return null;
        }

        User user = UserFactory.createUser(name, email, role);
        userRepo.saveUser(user, password);
        return user;
    }

}