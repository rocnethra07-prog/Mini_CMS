package course_manager_v1.model;

import java.util.Objects;

public class Enrollment {
    private Course course;
    private LessonProgress lessonProgress;
    private AssignmentProgress assignmentProgress;

    public Enrollment(Course course) {
        this.course = course;
    }

    public Course getCourse(){
        return course;
    }

    public LessonProgress getLessonProgress() {
        return lessonProgress;
    }

    public AssignmentProgress getAssignmentProgress() {
        return assignmentProgress;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Enrollment enrollment)) return false;
        return Objects.equals(course, enrollment.course);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(course);
    }
}
