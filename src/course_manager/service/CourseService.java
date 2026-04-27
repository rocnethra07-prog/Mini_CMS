package course_manager.service;

import course_manager.model.Course;
import course_manager.model.Instructor;
import course_manager.repository.CourseRepo;

import java.util.Set;

public class CourseService {

    private final CourseRepo courseRepo = new CourseRepo();
    public boolean updateCourse(Course course, String title, String description, double price, Set<String> categories){
        course.setTitle(title);
        course.setDescription(description);
        course.setPrice(price);
        course.setCategories(categories);
        return true;
    }

    public boolean deleteCourse(Course course, Instructor instructor){
        if( course.getInstructor() == null || !course.getInstructor().equals(instructor)){
            return false;
        }
        instructor.removeCourse(course);
        return courseRepo.delete(course);
    }
}
