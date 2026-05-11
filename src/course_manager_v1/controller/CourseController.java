package course_manager_v1.controller;


import course_manager_v1.model.*;
import course_manager_v1.service.CourseService;
import course_manager_v1.util.InputUtil;
import course_manager_v1.util.Validator;

import java.util.*;

public class CourseController {

    private final CourseService courseService = new CourseService();

    public void showMenu(Course course, Scanner sc) {
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
                    updateCourse(course, sc);
                    break;
                case "3":
                    boolean isDeleted = deleteCourse(course,sc);
                    if (isDeleted) {
                        back = true;
                    }
                    break;
                case "4":
                    manageLessons(course, sc);
                    break;
                case "5":
                    manageAssignments(course, sc);
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

    private void updateCourse(Course course, Scanner sc) {

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

        boolean updated = courseService.updateCourse(course, newTitle, newDescription, newPrice, categories);
        if (updated) {
            System.out.println("Course updated successfully.");
        }
    }

    private boolean deleteCourse(Course course, Scanner sc) {
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


    private void manageLessons(Course course, Scanner sc) {
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
                    addLesson(course, sc);
                    break;
                case "3":
                    editLesson(course, sc);
                    break;
                case "4":
                    deleteLesson(course,sc);
                    break;
                case "5":
                    manageResourcesMenu(course, sc);
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

    private void addLesson(Course course, Scanner sc){

        String title = InputUtil.getTitle(
                sc, "Enter Lesson Title : ", "Title must be at least 3 characters."
        );

        String content = InputUtil.getNonEmptyInput(
                sc, "Enter Lesson Content : ", "Content cannot be empty."
        );

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

    private void deleteLesson(Course course , Scanner sc){
        listLessons(course);

        String lessonId = InputUtil.getNonEmptyInput(
                sc, "Enter Lesson ID to delete : ", "Lesson ID cannot be empty."
        );

        boolean deleted = courseService.deleteLesson(course, lessonId);

        if(deleted){
            System.out.println("Lesson deleted successfully ");
        } else {
            System.out.println("Lesson not found ");
        }
    }

    private void editLesson(Course course, Scanner sc){
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

    private void manageResourcesMenu(Course course, Scanner sc){
        listLessons(course);

        String lessonId = InputUtil.getNonEmptyInput(
                sc, "Enter Lesson ID to manage resources : ", "Lesson ID cannot be empty."
        );

        Lesson lesson = courseService.getLessonById(course, lessonId);

        if(lesson == null){
            System.out.println("Lesson not found");
            return;
        }

        boolean back = false;
        while(!back){
            System.out.println("Manage resources of \"" + lesson.getTitle() + "\" : ");
            System.out.println("1. View resources");
//            System.out.println(". Open Resource");
            System.out.println("2. Add resource ");
            System.out.println("3. Remove resource");
            System.out.println("0. Back");

            System.out.print("Choose: ");
            switch(sc.nextLine().trim()){
                case "1":
                    viewResources(lesson);
                    break;
                case "2":
                    addResource(lesson, sc);
                    break;
                case "3":
                    deleteResource(lesson, sc);
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

    private void addResource(Lesson lesson, Scanner sc) {

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

    private void deleteResource(Lesson lesson, Scanner sc){
        viewResources(lesson);
        String id = InputUtil.getNonEmptyInput(
                sc, "Enter Resource ID to delete : ", "Resource ID cannot be empty."
        );

        boolean removed = courseService.removeResourceFromLesson(lesson, id);

        if(removed){
            System.out.println("Resource deleted successfully ");
        } else {
            System.out.println("Resource not found ");
        }
    }

    private void manageAssignments(Course course, Scanner sc){
        System.out.println("\nManaging Assignments of " + course.getTitle());
        boolean back = false;

        while(!back){
            System.out.println("\nAssignment Menu");
            System.out.println("1. List Assignments");
            System.out.println("2. Add Assignment");
            System.out.println("3. Remove Assignment");
            System.out.println("0. Back");

            System.out.print("Choose: ");
            switch (sc.nextLine().trim()){
                case "1":
                    listAssignments(course);
                    break;
                case "2":
                    addAssignment(course, sc);
                    break;
                case "3":
                    removeAssignment(course, sc);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }

    private void listAssignments(Course course) {
        List<Assignment> assignments = courseService.getAssignments(course);

        if (assignments.isEmpty()) {
            System.out.println("No assignments in this course");
            return;
        }

        for (Assignment a : assignments) {
            System.out.println(a);
        }
    }


    private void addAssignment(Course course, Scanner sc){
        String title;
        while(true){
            System.out.print("Enter quiz title: ");
            title = sc.nextLine().trim();
            if(title.isEmpty()){
                System.out.println("Title cannot be empty.");
                continue;
            }
            break;
        }

        Assignment assignment = courseService.addAssignment(course, title);

        while(true){

            String questionText;
            while(true){
                System.out.print("Enter question: ");
                questionText = sc.nextLine().trim();
                if(questionText.isEmpty()){
                    System.out.println("Question cannot be empty.");
                    continue;
                }

                if(courseService.isDuplicateQuestion(assignment, questionText)){
                    System.out.println("Duplicate question not allowed.");
                    continue;
                }
                break;
            }

            List<String> optionsList = new ArrayList<>();

            for(int i=1 ; i<=2 ; i++){
                while (true){
                    System.out.print("Enter option " + i + " : ");
                    String option = sc.nextLine().trim();

                    if(option.isEmpty()){
                        System.out.println("Option cannot be empty.");
                        continue;
                    }

                    if(optionsList.stream().anyMatch(o -> o.equalsIgnoreCase(option))){
                        System.out.println("Duplicate option not allowed");
                        continue;
                    }

                    optionsList.add(option);
                    break;
                }
            }

            while (true){
                System.out.print("Add another option? (y/n): ");
                String choice = sc.nextLine().trim();

                if(choice.equalsIgnoreCase("n")) break;
                if(!choice.equalsIgnoreCase("y")){
                    System.out.println("Invalid option. Try again. ");
                    continue;
                }

                while (true){
                    System.out.print("Enter option " + optionsList.size() + " : ");
                    String option = sc.nextLine().trim();

                    if(option.isEmpty()){
                        System.out.println("Option cannot be empty.");
                        continue;
                    }

                    if(optionsList.contains(option)){
                        System.out.println("Duplicate option not allowed");
                        continue;
                    }
                    optionsList.add(option);
                    break;
                }
            }

            int correctIndex;
            while(true){

                for(int i = 0; i < optionsList.size(); i++){
                    System.out.println((i+1) + ". " + optionsList.get(i));
                }

                System.out.print("Enter correct option number: ");

                try{
                    correctIndex = Integer.parseInt(sc.nextLine().trim()) - 1;

                    if(correctIndex >= 0 && correctIndex < optionsList.size()){

                        System.out.println("Selected: " + optionsList.get(correctIndex));

                        System.out.print("Confirm? (y/n): ");
                        String confirm = sc.nextLine().trim();

                        if(confirm.equalsIgnoreCase("y")) break;
                        if(confirm.equalsIgnoreCase("n")) continue;
                    }

                }catch(Exception ignored){}

                System.out.println("Invalid choice.");
            }

            int mark = 1;
            while(true){
                System.out.print("Enter marks (press Enter for default 1): ");
                String input = sc.nextLine().trim();

                if(input.isEmpty()) break;

                try{
                    int val = Integer.parseInt(input);
                    if(val > 0){
                        mark = val;
                        break;
                    }
                }catch(Exception ignored){}

                System.out.println("Invalid marks.");
            }

            courseService.createAndAddQuestion(assignment,questionText, optionsList, correctIndex, mark);

            System.out.print("Add another question? (y/n): ");
            if(sc.nextLine().trim().equalsIgnoreCase("n")){
                break;
            }

        }
        System.out.println("Assignment created successfully ");
    }

    private void removeAssignment(Course course, Scanner sc){
        listAssignments(course);


        String id = InputUtil.getNonEmptyInput(
                sc, "Enter Assignment ID to delete : ", "Assignment ID cannot be empty."
        );

        boolean removed = courseService.removeAssignment(course, id);

        if(removed){
            System.out.println("Assignment deleted successfully");
        }
        else {
            System.out.println("Assignment not found");
        }
    }
}