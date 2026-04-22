package course_manager.controller;

import course_manager.model.Instructor;
import course_manager.model.User;

public class InstructorController implements Controller{

    public void showMenu(User user){
        Instructor instructor = (Instructor)user;
        System.out.println("instructor menu");
    }

}
