package course_manager_v1.repository;

import java.util.ArrayList;
import java.util.List;
import course_manager_v1.model.Course;
import course_manager_v1.model.Student;


public class CourseRepo {

    private static final List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public Course findById(String id) {
        for (Course c : courses) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public boolean delete(Course course) {
        return courses.remove(course);
    }

    public List<Course> getAll() {
        return new ArrayList<>(courses);
    }

    public List<Course> findCoursesByCategory(String categoryInput){
        return courses.stream().filter(course -> course.getCategories().stream()
                .anyMatch(category -> category.equalsIgnoreCase(categoryInput))).toList();
    }

    public List<Course> findCoursesByMaxPrice(double price){
        return courses.stream().filter(course -> course.getPrice() <= price).toList();
    }

    public List<Course> findCoursesByCategoryAndPrice(String categoryInput, double price){
        return courses.stream()
                .filter(course -> course.getCategories().stream()
                        .anyMatch(category -> category.equalsIgnoreCase(categoryInput)))
                .filter(course ->  course.getPrice() <= price).toList();
    }

    public List<Course> getEnrollableCourses(Student student){
        return courses.stream()
                .filter(course -> !student.getMyEnrolledCourses().contains(course))
                .toList();
    }
}