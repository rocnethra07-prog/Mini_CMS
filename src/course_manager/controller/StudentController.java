package course_manager.controller;

import course_manager.model.Student;
import course_manager.model.User;

public class StudentController implements Controller {

    public void showMenu(User user) {
        Student student = (Student)user;
        System.out.println("student menu");
    }

}
