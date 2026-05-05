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
        return new ArrayList<>(myCreatedCourses);
    }

    public void addCourse(Course course){
        this.myCreatedCourses.add(course);
        course.setInstructor(this);
    }

    public void removeCourse(Course course){
        this.myCreatedCourses.remove(course);
        course.setInstructor(null);
    }
}
