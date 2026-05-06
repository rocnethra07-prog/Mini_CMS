package course_manager_v1.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends User{

    private List<Enrollment> enrollmentList;

    public Student(String name, String email) {
        super(name, email, Role.STUDENT);
        this.enrollmentList = new ArrayList<>();
    }

    public List<Enrollment> getMyEnrollments(){
        return enrollmentList;
    }

    public void addEnrollment(Enrollment enrollment){
        if(!this.enrollmentList.contains(enrollment)){
            this.enrollmentList.add(enrollment);
        }
    }
}
