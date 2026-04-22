package course_manager.model;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends User {

    private List<Course> myCreatedCourses;

    public Instructor(String name, String email) {
        super(name, email);
        this.myCreatedCourses = new ArrayList<>();
    }

    public List<Course> getMyCreatedCourses() {
        return myCreatedCourses;
    }

    public void setMyCreatedCourses(List<Course> myCreatedCourses) {
        this.myCreatedCourses = myCreatedCourses;
    }
}
