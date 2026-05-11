package course_manager_v1.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student extends User{

    private final List<Enrollment> enrollmentList = new ArrayList<>();

    public Student(String name, String email) {
        super(name, email, Role.STUDENT);
    }

    public List<Enrollment> getMyEnrollments(){
        return Collections.unmodifiableList(enrollmentList);
    }

    public void addEnrollment(Enrollment enrollment){
        if(!this.enrollmentList.contains(enrollment)){
            this.enrollmentList.add(enrollment);
        }
    }
}
