package course_manager.model;

import course_manager.util.IdGenerator;

public class User {
    private final String id;
    private String name;
    private String email;


    public User(String name, String email){
        this.email = email;
        this.name = name;
        this.id = IdGenerator.generateUserId();
    }

    public String getId(){
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
