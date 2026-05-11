package course_manager_v1.util;

import course_manager_v1.model.Instructor;
import course_manager_v1.model.Role;
import course_manager_v1.model.Student;
import course_manager_v1.model.User;

public class UserFactory {
    public static User createUser(String name, String email, Role role){
        return switch (role){
            case STUDENT -> new Student(name, email);
            case INSTRUCTOR -> new Instructor(name, email);
            default -> throw new IllegalArgumentException("Invalid role");
        };
    }
}
