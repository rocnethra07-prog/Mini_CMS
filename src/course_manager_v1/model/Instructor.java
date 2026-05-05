package course_manager_v1.model;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends User {

    private List<Course> myCreatedCourses;

    public Instructor(String name, String email) {
        super(name, email, Role.INSTRUCTOR);
        this.myCreatedCourses = new ArrayList<>();
    }

    public List<Course> getMyCreatedCourses() {
        return myCreatedCourses;
    }

    public void addCourse(Course course){
        if(!this.myCreatedCourses.contains(course)){
            this.myCreatedCourses.add(course);
            course.setInstructor(this);
        }
    }

    public void removeCourse(Course course){
        this.myCreatedCourses.remove(course);
        course.setInstructor(null);
    }
}