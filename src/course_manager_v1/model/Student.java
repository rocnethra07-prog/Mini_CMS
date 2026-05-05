package course_manager_v1.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends User{

    private List<Course> myEnrolledCourses;

    public Student(String name, String email){
        super(name, email, Role.STUDENT);
        this.myEnrolledCourses = new ArrayList<>();
    }

    public List<Course> getMyEnrolledCourses() {
        return myEnrolledCourses;
    }

    public void addEnrollment(Course course){
        if(!this.myEnrolledCourses.contains(course)){
            this.myEnrolledCourses.add(course);
        }
    }
}
