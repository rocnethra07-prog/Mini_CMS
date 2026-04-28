package course_manager_v2.model;

public class Credential {
    private String passwordHash;

    public Credential(String rawPassword){
        this.passwordHash = hash(rawPassword);
    }

    public boolean validatePassword(String inputPassword){
        return passwordHash.equals(hash(inputPassword));
    }

    public void changePassword(String oldPassword, String newPassword){
        if(!validatePassword(oldPassword)){
            throw new IllegalArgumentException("Incorrect old password");
        }
        this.passwordHash = hash(newPassword);
    }

    private String hash(String password){
        return String.valueOf(password.hashCode());
    }
}