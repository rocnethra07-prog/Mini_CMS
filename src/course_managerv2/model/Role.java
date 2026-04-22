package course_managerv2.model;

import course_managerv2.controller.Controller;
import course_managerv2.controller.InstructorController;
import course_managerv2.controller.StudentController;

public enum Role {
    STUDENT{
        @Override
        public Controller getController(){
            return new StudentController();
        }
    },
    INSTRUCTOR{
        @Override
        public Controller getController(){
            return new InstructorController();
        }
    };

    public abstract Controller getController();


}
