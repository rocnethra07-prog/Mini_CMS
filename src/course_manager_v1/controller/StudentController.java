package course_manager_v1.controller;

import course_manager_v1.model.Student;

import java.util.List;
import java.util.Scanner;
import course_manager_v1.service.StudentService;
import course_manager_v1.model.Course;

public class StudentController implements UserController<Student>{

    private final Scanner sc = new Scanner(System.in);
    private final StudentService studentService = new StudentService();

    public void showMenu(Student student) {
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
                    searchCourses(student);
                    break;
                case "3":
                    enrollCourse(student);
                    break;
                case "4":
                    openCourse(student);
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

    private void searchCourses(Student student){
        System.out.println("\n--- Search Courses ---");
        System.out.println("1. Search by Category");
        System.out.println("2. Search by Price");
        System.out.println("3. Search by Category and Price");

        System.out.print("Choose : ");
        String choice = sc.nextLine().trim();
        switch (choice) {
            case "1":
                searchByCategory(student);
                break;
            case "2":
                searchByMaxPrice(student);
                break;
            case "3":
                searchByCategoryAndPrice(student);
                break;
            case "0":
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void searchByCategory(Student student){
        System.out.print("Enter category to search: ");
        String category = sc.nextLine().trim();

        if (category.isEmpty()) {
            System.out.println("Category cannot be empty.");
            return;
        }
        List<Course> courseList = studentService.searchByCategory(category);
        printSearchResult(student, courseList, "Category: "+ category);
    }

    private void searchByMaxPrice(Student student){
        double price;

        while(true){
            System.out.print("Enter max price (0 for free courses): ");
            String input = sc.nextLine().trim();

            try{
                price = Double.parseDouble(input);
                if(price >= 0){
                    break;
                }
            }
            catch (NumberFormatException e) {

            }
            System.out.println("Enter a valid price. ");

        }

        List<Course> courseList = studentService.searchByMaxPrice(price);

        String label = price == 0 ? "Free Courses" : "Max Price: " + price;

        printSearchResult(student,courseList,label);
    }

    private void searchByCategoryAndPrice(Student student){
        System.out.print("Enter category: ");
        String category = sc.nextLine().trim();

        if(category.isEmpty()){
            System.out.println("Category cannot be empty.");
            return;
        }

        double maxPrice;

        while(true){
            System.out.print("Enter max price (0 for free) : ");
            String input = sc.nextLine().trim();

            try {
                maxPrice = Double.parseDouble(input);
                if(maxPrice >= 0){
                    break;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
            System.out.println("Enter a valid price (0 or above).");
        }

        List<Course> courseList = studentService.searchByCategoryAndMaxPrice(category, maxPrice);
        String label = "Category: " + category + (maxPrice == 0 ? " | Free only" : " | Max price: $" + String.format("%.2f", maxPrice));
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

    private void enrollCourse(Student student){
        System.out.println("\nEnroll in a course---");

        List<Course> courses = studentService.getEnrollableCourses(student);

        if(courses.isEmpty()){
            System.out.println("No courses available to enroll.");
            return;
        }

        for(Course course : courses){
            System.out.println(course);
        }

        System.out.print("\nEnter Course ID: ");
        String courseId = sc.nextLine().trim();

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

    private void openCourse(Student student){
        System.out.println("\n--- My Enrolled Courses ---");
        List<Course> enrolled = studentService.getEnrolledCourses(student);

        if(enrolled.isEmpty()){
            System.out.println("You are not enrolled in any course");
            return;
        }

        for (Course c : enrolled) {
            System.out.println(c);
        }

        System.out.print("Enter Course ID: ");
        String id = sc.nextLine();

        Course course = enrolled.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (course == null) {
            System.out.println("Invalid ID");
            return;
        }


    }

}
