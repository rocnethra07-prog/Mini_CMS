package course_managerv2.repository;

import course_managerv2.model.User;
import course_managerv2.model.Credential;

import java.util.HashMap;
import java.util.Map;

public class UserRepo {
    private static final Map<String, User> userStore = new HashMap<>();
    private static final Map<String, Credential> credentialStore = new HashMap<>();

    public User findUserByEmail(String email){
        return userStore.get(email);
    }

    public void saveUser(User user, String password){
        userStore.put(user.getEmail(), user);
        credentialStore.put(user.getId(), new Credential(password));
    }

    public boolean validateCredentials(String email, String password){
        User user = findUserByEmail(email);
        if(user == null){
            return false;
        }
        Credential credential = credentialStore.get(user.getId());
        return  credential != null && credential.validatePassword(password);
    }
}
