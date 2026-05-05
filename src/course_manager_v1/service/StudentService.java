package course_manager_v1.service;

import course_manager_v1.model.Course;
import course_manager_v1.repository.CourseRepo;
import course_manager_v1.model.Student;


import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final CourseRepo courseRepo = new CourseRepo();

    public List<Course> getAllCourses(){
        return courseRepo.getAll();
    }

    public String formatCourseWithStatus(Student student, Course course){
        if (student.getMyEnrolledCourses().contains(course)) {
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
        return new ArrayList<>(student.getMyEnrolledCourses());
    }

    public List<Course> getEnrollableCourses(Student student){
        return courseRepo.getEnrollableCourses(student);
    }

    public Course findCourseFromList(List<Course> courses, String id){
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean enroll( Student student, Course course){
        if(student.getMyEnrolledCourses().contains(course)){
            return false;
        }
        student.addEnrollment(course);
        return true;
    }
}