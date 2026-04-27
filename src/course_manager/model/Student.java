package course_manager.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends User{

    private List<Course> myEnrolledCourses;

    public Student(String name, String email){
        super(name, email);
        this.myEnrolledCourses = new ArrayList<>();
    }

    public List<Course> getMyEnrolledCourses() {
        return myEnrolledCourses;
    }

    public void setMyEnrolledCourses(List<Course> myEnrolledCourses) {
        this.myEnrolledCourses = myEnrolledCourses;
    }

}
