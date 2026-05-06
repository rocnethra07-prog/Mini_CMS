package course_manager_v1.model;

import course_manager_v1.util.IdGenerator;

import java.util.*;

public class Course {
    private String id;
    private String title;
    private String description;
    private double price;
    private Instructor instructor;
    private Set<String> categories;
    private List<Lesson> lessons;
    private List<Assignment> assignments;

    public Course(String title, String description, double price, Set<String> categories, Instructor instructor){
        this.title = title;
        this.description = description;
        this.price = price;
        this.categories = categories;
        this.id = IdGenerator.generateCourseId();
        instructor.addCourse(this);
        this.lessons = new ArrayList<>();
        this.assignments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public String toString() {
        return "[ ID: " + id + " | Title: " + title + " | Price: "
                + (isFree() ? "Free" : "$" + String.format("%.2f", price)) ;
    }

    public boolean isFree(){
        return this.price == 0.0;
    }

    public List<Lesson> getLessons() {
        return new ArrayList<>(lessons);
    }

    public void addLesson(Lesson lesson){
        this.lessons.add(lesson);
    }

    public void deleteLesson(Lesson lesson){
        this.lessons.remove(lesson);
    }

    public List<Assignment> getAssignments(){
        return new ArrayList<>(assignments);
    }

    public void addAssignment(Assignment assignment){
        this.assignments.add(assignment);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Course course)) return false;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public boolean removeAssignment(Assignment assignment) {
        return this.assignments.remove(assignment);
    }
}