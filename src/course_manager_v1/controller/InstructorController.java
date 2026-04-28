package course_manager_v1.controller;

import course_manager_v1.model.Instructor;

public class InstructorController implements UserController<Instructor> {

    public void showMenu(Instructor user){
        System.out.println("instructor");
    }

}
