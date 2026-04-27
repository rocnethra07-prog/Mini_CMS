package course_manager.repository;

import course_manager.model.Course;

import java.util.ArrayList;
import java.util.List;

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
}
