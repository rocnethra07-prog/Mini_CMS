package course_manager_v1.util;

import course_manager_v1.controller.InstructorController;
import course_manager_v1.controller.StudentController;
import course_manager_v1.controller.UserController;
import course_manager_v1.model.Role;
import course_manager_v1.model.User;

public class ControllerProvider {

    @SuppressWarnings("unchecked")
    public static <T extends User> UserController<T> getUserController(User user){
        if(user.getRole() == Role.STUDENT){
            return (UserController<T>)new StudentController();
        }
        if(user.getRole() == Role.INSTRUCTOR){
            return (UserController<T>) new InstructorController();
        }
        throw new IllegalArgumentException("No controller found for: " + user);
    }
}