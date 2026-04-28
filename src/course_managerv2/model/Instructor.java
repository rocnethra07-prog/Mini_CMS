package course_managerv2.model;

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

    public void setMyCreatedCourses(List<Course> myCreatedCourses) {
        this.myCreatedCourses = myCreatedCourses;
    }
}