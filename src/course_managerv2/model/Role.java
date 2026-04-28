package course_managerv2.model;

import course_managerv2.controller.UserController;
import course_managerv2.controller.InstructorController;
import course_managerv2.controller.StudentController;

public enum Role {
    STUDENT{
        @Override
        public UserController getController(){
            return new StudentController();
        }
    },
    INSTRUCTOR{
        @Override
        public UserController getController(){
            return new InstructorController();
        }
    };

    public abstract UserController getController();

}
