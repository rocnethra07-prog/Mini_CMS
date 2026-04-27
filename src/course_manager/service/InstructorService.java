package course_manager.service;

import course_manager.model.Instructor;
import course_manager.repository.CourseRepo;
import course_manager.model.Course;

import java.util.List;
import java.util.Set;

public class InstructorService {
    private final CourseRepo courseRepo = new CourseRepo();

    public List<Course> getMyCourses(Instructor instructor){
        return instructor.getMyCreatedCourses();
    }

    public Course createCourse(String title, String description, Set<String> categories, double price, Instructor instructor) {

        Course course = new Course(title, description,price, categories, instructor);

        courseRepo.addCourse(course);

        return course;
    }

    public Course getCourseById(String id) {
        return courseRepo.findById(id);
    }

    public Course getInstructorCourseById(Instructor instructor, String courseId){
        return instructor.getMyCreatedCourses()
                .stream()
                .filter(c -> c.getId().equals(courseId))
                .findFirst()
                .orElse(null);
    }

}
