package course_manager_v1.model;

import course_manager_v1.util.IdGenerator;

public  class User {
    private final String id;
    private String name;
    private String email;
    private Role role;

    public User(String name, String email, Role role){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if(role == null){
            throw new IllegalArgumentException("Role cannot be null");
        }
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

    public String getName() {
        return name;
    }

    public Role getRole(){
        return role;
    }
}