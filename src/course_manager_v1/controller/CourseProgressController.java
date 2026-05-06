package course_manager_v1.controller;

import course_manager_v1.model.Course;
import course_manager_v1.model.Lesson;
import course_manager_v1.model.Resource;
import course_manager_v1.model.Student;
import course_manager_v1.service.StudentService;
import course_manager_v1.util.ResourceOpener;

import java.util.List;
import java.util.Scanner;

public class CourseProgressController {

    private final Scanner sc = new Scanner(System.in);
    private final StudentService studentService = new StudentService();

    public void start(Student student) {
        System.out.println("\n--- My Enrolled Courses ---");
        List<Course> enrolled = studentService.getEnrolledCourses(student);

        if (enrolled.isEmpty()) {
            System.out.println("You are not enrolled in any course");
            return;
        }

        for (Course c : enrolled) {
            System.out.println(c);
        }

        System.out.print("Enter Course ID: ");
        String id = sc.nextLine().trim();

        Course course = enrolled.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (course == null) {
            System.out.println("Invalid ID");
            return;
        }

        openCourseMenu(student, course);
    }

    private void openCourseMenu(Student student, Course course) {

        boolean back = false;
        while (!back) {
            System.out.println("\n--- " + course.getTitle() + " ---");
            System.out.println("1. Open Lessons");
            System.out.println("2. Open Assignments (Coming Soon)");
            System.out.println("3. Show Progress");
            System.out.println("0. Back");

            System.out.print("Choose: ");

            switch (sc.nextLine().trim()) {
                case "1":
                    openLessons(student, course);
                    break;
                case "2":
                    System.out.println("Assignments module not implemented yet.");
                    break;
                case "3":
//                    showProgress(student, course);
                    System.out.println("progress dashboard not implemented yet");
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }


    private void openLessons(Student student, Course course){
        List<Lesson> lessonList = studentService.getLessons(course);
        if(lessonList.isEmpty()){
            System.out.println("No lessons in this course.");
            return;
        }

        boolean back = false;
        while(!back) {
            System.out.println("\n--- Lessons ---");
            for (Lesson lesson : lessonList) {
                boolean done = studentService.isLessonCompleted(student, course, lesson);
                System.out.println(lesson + (done ? " ✅" : ""));
            }

            System.out.println("1. Open Lesson");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            switch (sc.nextLine().trim()) {
                case "1":
                    System.out.print("Enter Lesson ID: ");
                    String id = sc.nextLine().trim();

                    Lesson lesson = lessonList.stream()
                            .filter(l -> l.getId().equals(id))
                            .findFirst()
                            .orElse(null);

                    if(lesson == null){
                        System.out.println("Invalid lesson ID.");
                        continue;
                    }
                    openLessonMenu(student,course, lesson);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void openLessonMenu(Student student,Course course, Lesson lesson){
        System.out.println(lesson.getTitle());
        System.out.println("Content:\n" + lesson.getContent());


        if(lesson.getResourceList().isEmpty()){
            System.out.println("\n- No Resources - ");
        }
        else{
            for(Resource r : lesson.getResourceList()){
                System.out.println(r);
            }
        }

        boolean back = false;
        while(!back){
            System.out.println("1. Open Resource ");
            System.out.println("2. Mark as complete");
            System.out.println("0. Back");

            System.out.print("Choose:");
            switch (sc.nextLine().trim()) {
                case "1":
                    openResource(lesson);
                    break;
                case "2":
                    boolean success = studentService.markLessonCompleted(student, course, lesson);
                    if(success){
                        System.out.println("Completed ✔");
                    } else {
                        System.out.println("Already completed");
                    }
                    break;
                case "0":
                    back = true;
                    break;
            }
        }
    }

    private void openResource(Lesson lesson){
        if(lesson.getResourceList().isEmpty()){
            System.out.println("No resources.");
            return;
        }

        System.out.print("Enter Resource ID: ");
        String id = sc.nextLine().trim();

        for(Resource r : lesson.getResourceList()){
            if(r.getId().equals(id)){
                studentService.openResource(r.getUrl());
            }
        }

        System.out.println("Resource not found.");
    }

}
