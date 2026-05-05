package course_manager_v1.service;

import course_manager_v1.repository.CourseRepo;
import course_manager_v1.model.Course;
import course_manager_v1.model.Instructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class InstructorService {
    private final CourseRepo courseRepo = new CourseRepo();

    public List<Course> getMyCourses(Instructor instructor) {
        return new ArrayList<>(instructor.getMyCreatedCourses());
    }

    public Course createCourse(String title, String description, Set<String> categories, double price, Instructor instructor) {

        Course course = new Course(title, description, price, categories, instructor);
        courseRepo.addCourse(course);
        return course;
    }

    public Course getCourseById(String id) {
        return courseRepo.findById(id);
    }

    public Course getInstructorCourseById(Instructor instructor, String courseId) {
        return instructor.getMyCreatedCourses()
                .stream()
                .filter(c -> c.getId().equals(courseId))
                .findFirst()
                .orElse(null);
    }
}