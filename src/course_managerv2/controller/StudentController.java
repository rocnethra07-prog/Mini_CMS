package course_managerv2.controller;

import course_managerv2.model.Student;
import course_managerv2.model.User;

public class StudentController implements Controller {

    public void showMenu(User user) {
        Student student = (Student)user;
        System.out.println("student");
    }

}
