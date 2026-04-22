package course_managerv2.model;

import course_managerv2.util.IdGenerator;

public class User {
    private final String id;
    private String name;
    private String email;
    private Role role;

    public User(String name, String email, Role role){
        this.email = email;
        this.name = name;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
