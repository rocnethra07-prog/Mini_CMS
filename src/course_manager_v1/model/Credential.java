package course_manager_v1.model;

public class Credential {
    private final String passwordHash;

    public Credential(String rawPassword){
        if(rawPassword == null || rawPassword.isBlank()){
            throw new IllegalArgumentException("Password cannot be empty");
        }
        this.passwordHash = hash(rawPassword);
    }

    public boolean validatePassword(String inputPassword){
        if(inputPassword == null){
            return false;
        }
        return passwordHash.equals(hash(inputPassword));
    }

    private String hash(String password){
        return String.valueOf(password.hashCode());
    }
}