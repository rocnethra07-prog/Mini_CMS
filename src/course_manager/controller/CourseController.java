package course_manager.controller;

import course_manager.model.Course;
import course_manager.model.Instructor;
import course_manager.service.CourseService;
import course_manager.util.Validator;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CourseController {

    private final Scanner sc = new Scanner(System.in);
    private final CourseService courseService = new CourseService();

    public void showMenu(Course course){
        boolean back = false;
        while(!back){
            System.out.println("Managing: " + course.getTitle());
            System.out.println("1. View course details");
            System.out.println("2. Edit Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Manage Lessons");
            System.out.println("5. Manage Assignments");
            System.out.println("0. Back");

            System.out.print("Choose : ");

            switch(sc.nextLine().trim()){
                case "1":
                    viewCourseDetails(course);
                    break;
                case "2":
                    updateCourse(course);
                    break;
                case "3":
                    boolean isDeleted = deleteCourse(course);
                    if(isDeleted){
                        back = true;
                    }
                    break;
                case "4":
                    manageLessons(course);
                    break;
                case "5":
                    manageAssignments(course);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }

    private void viewCourseDetails(Course course) {
        System.out.println("\n--- Course Details ---");
        System.out.println("ID : " + course.getId());
        System.out.println("Title : " + course.getTitle());
        System.out.println("Description : " + course.getDescription());
        System.out.println("Price : " + (course.isFree() ? "FREE" : String.format("$%.2f", course.getPrice())));
        System.out.println("Categories : "+ course.getCategories());
    }

    private void updateCourse(Course course){

        System.out.println("Updating :" + course);

        System.out.println("! Leave blank to keep current value !");
        System.out.println("Current title : " + course.getTitle());
        System.out.print("New title : ");
        String newTitle = sc.nextLine().trim();
        if(newTitle.isEmpty()){
            newTitle = course.getTitle();
        }

        System.out.println("Current description : " + course.getDescription());
        System.out.print("New Description : " );
        String newDescription = sc.nextLine().trim();
        if(newDescription.isEmpty()){
            newDescription = course.getDescription();
        }

        System.out.println("Current price : " + course.getPrice());
        double newPrice = course.getPrice();
        while(true){
            System.out.print("New price (0 for free) : ");
            String priceStr = sc.nextLine().trim();
            if(priceStr.isEmpty()){
                break;
            }
            if(Validator.isValidPrice(priceStr)){
                newPrice = Double.parseDouble(priceStr);
                break;
            }
            System.out.println("Enter a valid price");
        }

        Set<String> categories = new HashSet<>(course.getCategories());
        System.out.println("Current categories : " + categories);
        System.out.println("1. Keep");
        System.out.println("2. Add categories");
        System.out.println("3. Remove categories");
        System.out.println("4. Replace all");
        System.out.print("Choose option: ");

        switch(sc.nextLine().trim()){
            case "1":
                break;
            case "2":
                System.out.print("Enter categories to add (comma separated) :");
                String addCategories = sc.nextLine().trim();
                if(!addCategories.isEmpty()){
                    for(String category : addCategories.split(",")){
                        categories.add(category.trim());
                    }
                }
                break;
            case "3":
                System.out.print("Enter categories to remove (comma separated) : ");
                String removeStr = sc.nextLine().trim();
                if(!removeStr.isEmpty()){
                    for(String category : removeStr.split(",")){
                        categories.remove(category.trim());
                    }
                }
                break;
            case "4":
                System.out.print("Enter new categories (comma separated) : ");
                String newCategoriesStr = sc.nextLine().trim();
                categories.clear();
                if(!newCategoriesStr.isEmpty()){
                    for(String category : newCategoriesStr.split(",")){
                        categories.add(category.trim());
                    }
                }
                break;
            default:
                System.out.println("Invalid option. Keeping existing categories. ");
        }

        boolean updated = courseService.updateCourse(course, newTitle, newDescription,newPrice, categories);

        if (updated){
            System.out.println("Course updated successfully.");
        }
    }

    private boolean deleteCourse(Course course){
        boolean deleted ;

        while(true) {
            System.out.print("Are you sure? This will delete all lessons too. (y/n): ");
            String confirm = sc.nextLine().trim().toLowerCase();

            switch (confirm) {
                case "y":
                    Instructor instructor = course.getInstructor();
                    deleted = courseService.deleteCourse(course, instructor);
                    break;
                case "n":
                    System.out.println("Deletion Cancelled");
                    return false;
                default:
                    System.out.println("Wrong input.");
                    continue;
            }
            break;
        }
        if (deleted) {
            System.out.println("Course deleted.");
        }

        return deleted;
    }


    private void manageLessons(Course course){
        boolean back = false;
        while(!back) {
            System.out.println("\n Managing Lessons");
            System.out.println("1. List Lessons");
            System.out.println("2. Add Lesson");
            System.out.println("3. Edit Lesson");
            System.out.println("4. Delete Lesson");
            System.out.println("5. Manage Resources");
            System.out.println("0. Back");

            System.out.print("Choose: ");
            switch (sc.nextLine().trim()) {
                case "1":
                    listLessons(course);
                    break;
                case "2":
                    addLesson(course);
                    break;
                case "3":
//                    editLesson(course);
                    break;
                case "4":
                    deleteLesson(course);
                    break;
                case "5":
//                    manageResourcesMenu(course);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private void listLessons(Course course){

    }

    private void addLesson(Course course){}

    private void deleteLesson(Course course){}

    private void manageAssignments(Course course){
        System.out.println("Assignments management");
    }
}
