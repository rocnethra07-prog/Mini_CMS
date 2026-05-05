package course_manager_v1.controller;


import course_manager_v1.model.*;
import course_manager_v1.service.CourseService;
import course_manager_v1.util.ResourceOpener;
import course_manager_v1.util.Validator;

import java.sql.SQLOutput;
import java.util.*;

public class CourseController {

    private final Scanner sc = new Scanner(System.in);
    private final CourseService courseService = new CourseService();


    public void showMenu(Course course) {
        boolean back = false;
        while (!back) {
            System.out.println("Managing: " + course.getTitle());
            System.out.println("1. View course details");
            System.out.println("2. Edit Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Manage Lessons");
            System.out.println("5. Manage Assignments");
            System.out.println("0. Back");

            System.out.print("Choose : ");

            switch (sc.nextLine().trim()) {
                case "1":
                    viewCourseDetails(course);
                    break;
                case "2":
                    updateCourse(course);
                    break;
                case "3":
                    boolean isDeleted = deleteCourse(course);
                    if (isDeleted) {
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
        System.out.println("Categories : " + course.getCategories());
    }

    private void updateCourse(Course course) {

        System.out.println("Updating :" + course);

        System.out.println("! Leave blank to keep current value !");
        System.out.println("Current title : " + course.getTitle());
        System.out.print("New title : ");
        String newTitle = sc.nextLine().trim();
        if (newTitle.isEmpty()) {
            newTitle = course.getTitle();
        }

        System.out.println("Current description : " + course.getDescription());
        System.out.print("New Description : ");
        String newDescription = sc.nextLine().trim();
        if (newDescription.isEmpty()) {
            newDescription = course.getDescription();
        }

        System.out.println("Current price : " + course.getPrice());
        double newPrice = course.getPrice();
        while (true) {
            System.out.print("New price (0 for free) : ");
            String priceStr = sc.nextLine().trim();
            if (priceStr.isEmpty()) {
                break;
            }
            if (Validator.isValidPrice(priceStr)) {
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

        switch (sc.nextLine().trim()) {
            case "1":
                break;
            case "2":
                System.out.print("Enter categories to add (comma separated) :");
                String addCategories = sc.nextLine().trim();
                if (!addCategories.isEmpty()) {
                    for (String category : addCategories.split(",")) {
                        categories.add(category.trim());
                    }
                }
                break;
            case "3":
                System.out.print("Enter categories to remove (comma separated) : ");
                String removeStr = sc.nextLine().trim();
                if (!removeStr.isEmpty()) {
                    for (String category : removeStr.split(",")) {
                        categories.remove(category.trim());
                    }
                }
                break;
            case "4":
                System.out.print("Enter new categories (comma separated) : ");
                String newCategoriesStr = sc.nextLine().trim();
                categories.clear();
                if (!newCategoriesStr.isEmpty()) {
                    for (String category : newCategoriesStr.split(",")) {
                        categories.add(category.trim());
                    }
                }
                break;
            default:
                System.out.println("Invalid option. Keeping existing categories. ");
        }

        //check ::
        boolean updated = courseService.updateCourse(course, newTitle, newDescription, newPrice, categories);

        if (updated) {
            System.out.println("Course updated successfully.");
        }
    }

    private boolean deleteCourse(Course course) {
        boolean deleted;

        while (true) {
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


    private void manageLessons(Course course) {
        boolean back = false;
        while (!back) {
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
                    editLesson(course);
                    break;
                case "4":
                    deleteLesson(course);
                    break;
                case "5":
                    manageResourcesMenu(course);
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
        List<Lesson> lessonsList = courseService.getLessonsOfCourse(course);

        if(lessonsList.isEmpty()){
            System.out.println("No lessons available in this course");
            return;
        }

        System.out.println("\n--- Lessons--- ");
        for(Lesson lesson : lessonsList){
            System.out.println(lesson);
        }
    }

    private void addLesson(Course course){

        String title;
        while(true) {
            System.out.print("Title: ");
            title = sc.nextLine().trim();
            if (Validator.isValidTitle(title)) {
                break;
            }
            System.out.println("Invalid title (min 3 characters) ");
        }

        String content;
        while(true) {
            System.out.print("Content: ");
            content = sc.nextLine().trim();
            if(!content.isEmpty()){
                break;
            }
            System.out.println("Content cannot be empty.");
        }

        List<Resource> resources = new ArrayList<>();

        while (true) {
            System.out.print("Add resources? (y/n): ");
            String choice = sc.nextLine().trim();
            if(choice.equalsIgnoreCase("n")) break;

            if(!choice.equalsIgnoreCase("y")){
                System.out.println("Invalid choice. Enter y/n");
                continue;
            }

            ResourceType type = null;
            while (true){
                System.out.println("Choose Resource Type:");
                System.out.println("1. VIDEO");
                System.out.println("2. PDF");
                System.out.println("3. ARTICLE");
                System.out.println("4. LINK");
                System.out.println("5. IMAGE");

                System.out.print("Enter choice: ");
                String typeChoice = sc.nextLine().trim();

                switch (typeChoice){
                    case "1": type = ResourceType.VIDEO; break;
                    case "2": type = ResourceType.PDF; break;
                    case "3": type = ResourceType.ARTICLE; break;
                    case "4": type = ResourceType.LINK; break;
                    case "5": type = ResourceType.IMAGE; break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                        continue;
                }
                break;
            }
            String url;
            while (true){
                System.out.print("Enter resource URL: ");
                url = sc.nextLine().trim();

                boolean valid = switch (type){
                    case VIDEO -> Validator.isValidVideo(url);
                    case PDF -> Validator.isValidPDF(url);
                    case IMAGE -> Validator.isValidImage(url);
                    case ARTICLE -> Validator.isValidLink(url);
                    case LINK -> Validator.isValidLink(url);
                };

                if(valid) break;

                System.out.println("Invalid URL for type " + type + ". Try again.");
            }

            resources.add(new Resource(url, type));
            System.out.println("Resource added ");
        }

        courseService.addLesson(course, title, content, resources);
        System.out.println("Lesson added! ");
    }

    private void deleteLesson(Course course){
        listLessons(course);

        System.out.print("Enter Lesson ID to delete: ");
        String lessonId = sc.nextLine().trim();

        if(lessonId.isEmpty()){
            System.out.println("Invalid ID.");
            return;
        }

        boolean deleted = courseService.deleteLesson(course, lessonId);

        if(deleted){
            System.out.println("Lesson deleted successfully ");
        } else {
            System.out.println("Lesson not found ");
        }
    }

    private void editLesson(Course course){
        listLessons(course);
        System.out.print("Enter lesson ID to edit: ");
        String lessonId = sc.nextLine().trim();

        Lesson lesson = courseService.getLessonById(course, lessonId);
        if(lesson == null){
            System.out.println("Lesson not found");
            return;
        }

        System.out.println("Leave blank to keep existing values");
        System.out.println("Current title: " + lesson.getTitle());
        System.out.print("New title: ");
        String newTitle = sc.nextLine().trim();

        if(newTitle.isEmpty()){
            newTitle = lesson.getTitle();
        } else if(!Validator.isValidTitle(newTitle)){
            System.out.println("Invalid title. Keeping old value.");
            newTitle = lesson.getTitle();
        }

        System.out.println("Current content: " + lesson.getContent());
        System.out.print("New content: ");
        String newContent = sc.nextLine().trim();

        if(newContent.isEmpty()){
            newContent = lesson.getContent();
        }

        boolean updated = courseService.updateLesson(course, lessonId, newTitle, newContent);

        if(updated){
            System.out.println("Lesson updated successfully ");
        } else {
            System.out.println("Update failed ");
        }
    }

    private void manageResourcesMenu(Course course){
        listLessons(course);

        System.out.print("Enter lesson ID to manage resources: ");
        String lessonId = sc.nextLine().trim();

        Lesson lesson = courseService.getLessonById(course, lessonId);

        if(lesson == null){
            System.out.println("Lesson not found");
            return;
        }

        boolean back = false;
        while(!back){
            System.out.println("Manage resources of \"" + lesson.getTitle() + "\" : ");
            System.out.println("1. View resources");
            System.out.println("2. Open Resource");
            System.out.println("3. Add resource ");
            System.out.println("4. Remove resource");
            System.out.println("0. Back");

            System.out.print("Choose: ");
            switch(sc.nextLine().trim()){
                case "1":
                    viewResources(lesson);
                    break;
                case "2":
                    openResource(lesson);
                    break;
                case "3":
                    addResource(lesson);
                    break;
                case "4":
                    deleteResource(lesson);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }

    private void viewResources(Lesson lesson){
        List<Resource> resources = courseService.getResourceList(lesson);
        if(resources.isEmpty()){
            System.out.println("No resources in this lesson. ");
            return;
        }

        System.out.println("\n--- Resources ---");
        for(Resource resource : resources){
            System.out.println(resource);
        }
    }

    private void addResource(Lesson lesson) {

        ResourceType type = null;
        while (true) {
            System.out.println("Choose Resource Type:");
            System.out.println("1. VIDEO");
            System.out.println("2. PDF");
            System.out.println("3. ARTICLE");
            System.out.println("4. LINK");
            System.out.println("5. IMAGE");

            System.out.print("Enter choice: ");
            String typeChoice = sc.nextLine().trim();

            switch (typeChoice) {
                case "1":
                    type = ResourceType.VIDEO;
                    break;
                case "2":
                    type = ResourceType.PDF;
                    break;
                case "3":
                    type = ResourceType.ARTICLE;
                    break;
                case "4":
                    type = ResourceType.LINK;
                    break;
                case "5":
                    type = ResourceType.IMAGE;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    continue;
            }
            break;
        }

        String url;
        while (true) {
            System.out.print("Enter resource URL: ");
            url = sc.nextLine().trim();

            boolean valid = switch (type) {
                case VIDEO -> Validator.isValidVideo(url);
                case PDF -> Validator.isValidPDF(url);
                case IMAGE -> Validator.isValidImage(url);
                case ARTICLE -> Validator.isValidLink(url);
                case LINK -> Validator.isValidLink(url);
            };

            if (valid) break;
            System.out.println("Invalid URL for type " + type + ". Try again.");
        }

        courseService.addResourceToLesson(lesson, url, type);
        System.out.println("Resource added successfully.");
    }

    private void deleteResource(Lesson lesson){
        viewResources(lesson);
        System.out.print("Enter resource ID to delete: ");
        String id = sc.nextLine().trim();

        if(id.isEmpty()){
            System.out.println("Invalid ID.");
            return;
        }

        boolean removed = courseService.removeResourceFromLesson(lesson, id);

        if(removed){
            System.out.println("Resource deleted successfully ");
        } else {
            System.out.println("Resource not found ");
        }
    }

    private void openResource(Lesson lesson){
        viewResources(lesson);
        System.out.print("Enter resource ID to delete: ");
        String id = sc.nextLine().trim();

        if(id.isEmpty()){
            System.out.println("Invalid ID.");
            return;
        }

        Resource resource = courseService.getResourceById(lesson, id);
        if(resource == null){
            System.out.println("Resource not found.");
            return;
        }

        String url = resource.getUrl();
        if(!Validator.isValidURL(url)){
            System.out.println("Stored URL is invalid.");
            return;
        }

        ResourceOpener.openLink(url);
    }


    private void manageAssignments(Course course){

    }
}