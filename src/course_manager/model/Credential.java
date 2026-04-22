package course_manager.model;

public class Credential {
    private String password;

    public Credential(String password){
        this.password = password;
    }

    public boolean validatePassword(String password){
        return this.password.equals(password);
    }

}