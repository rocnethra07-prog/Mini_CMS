package course_manager_v1.service;

import java.util.List;
import java.util.Set;

import course_manager_v1.model.*;
import course_manager_v1.repository.CourseRepo;

public class CourseService {

    private final CourseRepo courseRepo = new CourseRepo();
    public boolean updateCourse(Course course, String title, String description, double price, Set<String> categories){
        course.setTitle(title);
        course.setDescription(description);
        course.setPrice(price);
        course.setCategories(categories);
        return true;
    }

    public boolean deleteCourse(Course course, Instructor instructor){
        if( course.getInstructor() == null || !course.getInstructor().equals(instructor)){
            return false;
        }
        instructor.removeCourse(course);
        return courseRepo.delete(course);
    }

    public List<Lesson> getLessonsOfCourse(Course course){
        return course.getLessons();
    }

    public void addLesson(Course course, String title, String content, List<Resource> resources){
        Lesson lesson = new Lesson(title, content, resources);
        course.addLesson(lesson);
    }

    public boolean deleteLesson(Course course, String lessonId){
        Lesson lesson = getLessonById(course, lessonId);

        if(lesson == null) return false;

        course.deleteLesson(lesson);
        return true;
    }

    public Lesson getLessonById(Course course, String lessonId){
        return course.getLessons()
                .stream()
                .filter(l -> l.getId().equals(lessonId))
                .findFirst()
                .orElse(null);
    }

    public boolean updateLesson(Course course, String lessonId, String newTitle, String newContent){
        Lesson lesson = getLessonById(course, lessonId);

        if(lesson == null) return false;

        lesson.setTitle(newTitle);
        lesson.setContent(newContent);

        return true;
    }

    public List<Resource> getResourceList(Lesson lesson){
        return lesson.getResourceList();
    }

    public void addResourceToLesson(Lesson lesson, String url, ResourceType type){
        Resource resource = new Resource(url,type);
        lesson.addResource(resource);
    }

    public boolean removeResourceFromLesson(Lesson lesson, String resourceId){
        return lesson.removeResource(resourceId);
    }

    public List<Assignment> getAssignments(Course course){
        return course.getAssignments();
    }

    public Assignment addAssignment(Course course, String title){
        Assignment newAssignment = new Assignment(title);
        course.addAssignment(newAssignment);
        return newAssignment;
    }

    public boolean isDuplicateQuestion(Assignment assignment, String questionText){
        return assignment.getQuestionsList().stream().anyMatch(q -> q.getQuestionText().equalsIgnoreCase(questionText));
    }

    public void createAndAddQuestion(Assignment assignment, String text, List<String> options, int correctIndex, int mark){
        assignment.addQuestion(new Question(text, options, correctIndex, mark));
    }

    public boolean removeAssignment(Course course, String assignmentId){
        Assignment assignment = getAssignmentById(course,assignmentId);
        if(assignment == null) return false;
        return course.removeAssignment(assignment);
    }

    public Assignment getAssignmentById(Course course, String id){
        return course.getAssignments()
                .stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}