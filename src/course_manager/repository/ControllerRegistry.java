package course_manager.repository;

import course_manager.controller.Controller;
import course_manager.controller.InstructorController;
import course_manager.controller.StudentController;
import course_manager.model.Instructor;
import course_manager.model.Student;
import course_manager.model.User;

import java.util.HashMap;
import java.util.Map;

public class ControllerRegistry {
    private static final Map<Class<? extends User>, Controller> controllersMap = new HashMap<>();

    static{
        controllersMap.put(Student.class, new StudentController());
        controllersMap.put(Instructor.class, new InstructorController());
    }

    public static Controller getController(User user){
        return controllersMap.get(user.getClass());
    }

}