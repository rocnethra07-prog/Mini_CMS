package course_manager_v2.model;

import course_manager_v2.controller.StudentController;

import java.util.ArrayList;
import java.util.List;

public class Student extends User{

    private List<Course> myEnrolledCourses;

    public Student(String name, String email){
        super(name, email, Role.STUDENT);
        this.myEnrolledCourses = new ArrayList<>();
    }

    public void showMenu(){
        new StudentController().showMenu(this);
    }

    public List<Course> getMyEnrolledCourses() {
        return new ArrayList<>(myEnrolledCourses);
    }
}
