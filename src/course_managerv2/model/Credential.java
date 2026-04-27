package course_managerv2.model;

public class Credential {
    private String password;

    public Credential(String password){
        this.password = password;
    }

    public boolean validatePassword(String password){
        return this.password.equals(password);
    }

    public void changePassword(String oldPassword, String newPassword){
        
    }
}