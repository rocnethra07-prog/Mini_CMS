package course_managerv2.controller;

import course_managerv2.model.Instructor;
import course_managerv2.model.User;

public class InstructorController implements Controller{

    public void showMenu(User user){
        Instructor instructor = (Instructor)user;
        System.out.println("instructor");
    }

}
