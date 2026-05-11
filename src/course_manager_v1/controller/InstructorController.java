package course_manager_v1.controller;

import course_manager_v1.model.Course;
import course_manager_v1.model.Instructor;
import course_manager_v1.service.InstructorService;
import course_manager_v1.util.InputUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InstructorController implements UserController<Instructor> {

    private final InstructorService instructorService = new InstructorService();

    public void showMenu(Instructor instructor, Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Instructor Menu ---");
            System.out.println("1. View My Courses");
            System.out.println("2. Manage Course");
            System.out.println("3. Create New Course");
            System.out.println("0. Back");
            System.out.print("Choose : ");
            switch (sc.nextLine().trim()) {
                case "1":
                    viewMyCourses(instructor);
                    break;
                case "2":
                    manageCourse(instructor, sc);
                    break;
                case "3":
                    createCourse(instructor, sc);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }


    private void viewMyCourses(Instructor instructor) {
        List<Course> courses = instructorService.getMyCourses(instructor);

        if (courses.isEmpty()) {
            System.out.println("No courses created yet.");
            return;
        }

        System.out.println("\n--- My Courses ---");
        for (Course c : courses) {
            System.out.println(c);
        }
    }

    private void createCourse(Instructor instructor, Scanner sc) {
        System.out.println("\n--- Create Course ---");

        String title = InputUtil.getTitle(
                sc,
                "Enter Course Title : ",
                "Title must be at least 3 characters."
        );

        System.out.print("Enter Course Description : ");
        String description = sc.nextLine().trim();

        System.out.print("Categories (comma separated): ");
        String categoriesStr = sc.nextLine().trim();

        Set<String> categories = new HashSet<>();
        if (!categoriesStr.isEmpty()) {
            String[] categoriesArr = categoriesStr.split(",");
            for (String category : categoriesArr) {
                categories.add(category.trim());
            }
        }

        double price = InputUtil.getPrice(
                sc,
                "Enter Price (0 for free) : ",
                "Enter a valid price (0 or above)."
        );


        Course course = instructorService.createCourse(title, description, categories, price, instructor);
        System.out.println("Course successfully created : " + course);
    }

    private void manageCourse(Instructor instructor, Scanner sc) {
        List<Course> myCourses = instructorService.getMyCourses(instructor);
        if (myCourses.isEmpty()) {
            System.out.println("You have not created any courses yet.");
            return;
        }

        for (Course c : myCourses) {
            System.out.println(c);
        }

        System.out.println("\n--- Manage Course---");

        String courseId = InputUtil.getNonEmptyInput(sc,"Enter Course ID to manage : ","Course ID cannot be empty");

        Course course = instructorService.getInstructorCourseById(instructor, courseId);

        if (course == null) {
            System.out.println("Invalid course ID or you do not own this course");
            return;
        }

        CourseController courseController = new CourseController();
        courseController.showMenu(course, sc);
    }
}