package course_manager_v2.controller;

import course_manager_v2.model.Student;

public class StudentController implements UserController<Student>{

    public void showMenu(Student user) {
        System.out.println("student");
    }

}
