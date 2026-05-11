package course_manager_v1.controller;

import course_manager_v1.model.Student;

import java.util.List;
import java.util.Scanner;
import course_manager_v1.service.StudentService;
import course_manager_v1.model.Course;
import course_manager_v1.util.InputUtil;

public class StudentController implements UserController<Student>{

    private final StudentService studentService = new StudentService();;
    public void showMenu(Student student, Scanner sc) {
        boolean back = false;
        while(!back){
            System.out.println("\n--- Student Menu --- ");
            System.out.println("1. Browse All Courses");
            System.out.println("2. Search Courses");
            System.out.println("3. Enroll in a new course");
            System.out.println("4. Open Course");
            System.out.println("5. View Enrolled Courses");
            System.out.println("0. Back");

            System.out.print("Choose: ");
            switch(sc.nextLine().trim()){
                case "1":
                    browseAllCourses(student);
                    break;
                case "2":
                    searchCourses(student, sc);
                    break;
                case "3":
                    enrollCourse(student, sc);
                    break;
                case "4":
                    new CourseProgressController().start(student, sc);
                    break;
                case "5":
                    viewEnrolledCourses(student);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again ");
            }
        }
    }

    private void browseAllCourses(Student student){
        List<Course> courses = studentService.getAllCourses();

        if(courses.isEmpty()){
            System.out.println("No courses available");
            return;
        }

        for(Course course : courses){
            printCourseWithStatus(student, course);
        }
    }

    private void printCourseWithStatus(Student student, Course course){
        System.out.println(studentService.formatCourseWithStatus(student, course));
    }

    private void searchCourses(Student student, Scanner sc){
        System.out.println("\n--- Search Courses ---");
        System.out.println("1. Search by Category");
        System.out.println("2. Search by Price");
        System.out.println("3. Search by Category and Price");

        System.out.print("Choose : ");
        String choice = sc.nextLine().trim();
        switch (choice) {
            case "1":
                searchByCategory(student, sc);
                break;
            case "2":
                searchByMaxPrice(student, sc);
                break;
            case "3":
                searchByCategoryAndPrice(student, sc);
                break;
            case "0":
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void searchByCategory(Student student, Scanner sc){

        String category = InputUtil.getNonEmptyInput(
                sc, "Enter category to search : ", "Category cannot be empty.");

        List<Course> courseList = studentService.searchByCategory(category);
        printSearchResult(student, courseList, "Category: "+ category);
    }

    private void searchByMaxPrice(Student student, Scanner sc){

        double price = InputUtil.getPrice(
                sc, "Enter max price (0 for free courses) : ", "Enter a valid price.");

        List<Course> courseList = studentService.searchByMaxPrice(price);
        String label = price == 0 ? "Free Courses" : "Max Price: " + price;

        printSearchResult(student,courseList,label);
    }

    private void searchByCategoryAndPrice(Student student, Scanner sc){

        String category = InputUtil.getNonEmptyInput(
                sc, "Enter category : ", "Category cannot be empty.");

        double maxPrice = InputUtil.getPrice(
                sc, "Enter max price (0 for free) : ", "Enter a valid price (0 or above).");

        List<Course> courseList = studentService.searchByCategoryAndMaxPrice(category, maxPrice);
        String label = "Category: " + category +
                (maxPrice == 0
                        ? " | Free only"
                        : " | Max price: $" + String.format("%.2f", maxPrice));
        printSearchResult(student, courseList, label);

    }

    private void printSearchResult(Student student, List<Course> results, String label) {

        System.out.println("\n--- Search Results [" + label + "] ---");

        if (results.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        for (Course course : results) {
            printCourseWithStatus(student, course);
        }
    }

    private void viewEnrolledCourses(Student student){
        System.out.println("\n--- My Enrolled Courses ---");

        List<Course> courses = studentService.getEnrolledCourses(student);

        if(courses.isEmpty()){
            System.out.println("You are not enrolled in any courses.");
            return;
        }

        for(Course course : courses){
            System.out.println(course);
        }
    }

    private void enrollCourse(Student student, Scanner sc){
        System.out.println("\nEnroll in a course---");

        List<Course> courses = studentService.getEnrollableCourses(student);

        if(courses.isEmpty()){
            System.out.println("No courses available to enroll.");
            return;
        }

        for(Course course : courses){
            System.out.println(course);
        }

        String courseId = InputUtil.getNonEmptyInput(
                sc, "\nEnter Course ID : ", "Course ID cannot be empty."
        );

        Course course = studentService.findCourseFromList(courses, courseId);

        if(course == null){
            System.out.println("Invalid course ID.");
            return;
        }

        boolean success = studentService.enroll(student, course);

        if(success){
            System.out.println("Successfully enrolled in \"" + course.getTitle() + "\"!");
        }
        else {
            System.out.println("You are already enrolled in this course.");
        }
    }
}
