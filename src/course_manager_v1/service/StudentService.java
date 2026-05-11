package course_manager_v1.service;

import course_manager_v1.model.Course;
import course_manager_v1.model.Enrollment;
import course_manager_v1.model.Lesson;
import course_manager_v1.repository.CourseRepo;
import course_manager_v1.model.Student;
import java.util.List;

public class StudentService {
    private final CourseRepo courseRepo = new CourseRepo();

    public List<Course> getAllCourses(){
        return courseRepo.getAll();
    }

    public String formatCourseWithStatus(Student student, Course course){
        if (isEnrolled(student, course)) {
            return course + " [ENROLLED]";
        }
        return course.toString();
    }

    public List<Course> searchByCategory(String category){
        return courseRepo.findCoursesByCategory(category);
    }

    public List<Course> searchByMaxPrice(double price){
        return courseRepo.findCoursesByMaxPrice(price);
    }

    public List<Course> searchByCategoryAndMaxPrice(String category, double maxPrice){
        return courseRepo.findCoursesByCategoryAndPrice(category, maxPrice);
    }

    public List<Course> getEnrolledCourses(Student student){
        return student.getMyEnrollments().stream().map(Enrollment::getCourse).toList();
    }

    public List<Course> getEnrollableCourses(Student student){
        return courseRepo.getAll().stream().filter(c -> !isEnrolled(student,c)).toList();
    }

    public Course findCourseFromList(List<Course> courses, String id){
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean enroll( Student student, Course course){
        if(isEnrolled(student,course)){
            return false;
        }
        student.addEnrollment(new Enrollment(course));
        return true;
    }

    private boolean isEnrolled(Student student, Course course){
        return student.getMyEnrollments().stream().anyMatch(e -> e.getCourse().equals(course));
    }

    public List<Lesson> getLessons(Course course){
        return course.getLessons();
    }

    public Enrollment getEnrollment(Student student, Course course){
        return student.getMyEnrollments().stream()
                .filter(e -> e.getCourse().equals(course))
                .findFirst()
                .orElse(null);
    }


    public boolean isLessonCompleted(Student student, Course course, Lesson lesson){
        Enrollment enrollment = getEnrollment(student, course);
        if(enrollment == null) return false;
        return enrollment.getLessonProgress().isCompleted(lesson);
    }

    public boolean markLessonCompleted(Student student, Course course, Lesson lesson){
        Enrollment enrollment = getEnrollment(student, course);
        if(enrollment == null) return false;
        return enrollment.getLessonProgress().markCompleted(lesson);
    }

}