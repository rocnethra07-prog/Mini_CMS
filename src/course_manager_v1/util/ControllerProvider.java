package course_manager_v1.util;

import course_manager_v1.controller.InstructorController;
import course_manager_v1.model.Instructor;
import course_manager_v1.controller.StudentController;
import course_manager_v1.controller.UserController;
import course_manager_v1.model.Student;
import course_manager_v1.model.User;

public class ControllerProvider {

    public static <T extends User> UserController<T> getUserController(User user){
        if(user instanceof Student){
            return (UserController<T>)new StudentController();
        }
        if(user instanceof Instructor){
            return (UserController<T>) new InstructorController();
        }
        throw new IllegalArgumentException("No controller found for: " + user.getClass());
    }
}
