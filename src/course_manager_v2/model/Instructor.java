package course_manager_v2.model;

import course_manager_v2.controller.InstructorController;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends User {

    private List<Course> myCreatedCourses;

    public Instructor(String name, String email) {
        super(name, email, Role.INSTRUCTOR);
        this.myCreatedCourses = new ArrayList<>();
    }

    public List<Course> getMyCreatedCourses() {
        return new ArrayList<>(myCreatedCourses);
    }

    public void showMenu(){
        new InstructorController().showMenu(this);
    }
}