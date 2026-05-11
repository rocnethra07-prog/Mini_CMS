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
    private final List<Student> enrolledStudents = new ArrayList<>();
    private final List<Lesson> lessons = new ArrayList<>();
    private final List<Assignment> assignments = new ArrayList<>();

    public Course(String title, String description, double price, Set<String> categories, Instructor instructor){
        if(instructor == null){
            throw new IllegalArgumentException("Instructor cannot be null");
        }

        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Title cannot be blank");
        }
        this.title = title;
        this.description = description;
        this.price = price;
        this.categories = new HashSet<>(categories);
        this.id = IdGenerator.generateCourseId();
        instructor.addCourse(this);
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
        return Collections.unmodifiableSet(categories);
    }

    public void setCategories(Set<String> categories) {
        this.categories = new HashSet<>(categories);
    }

    public String toString() {
        return "[ ID: " + id + " | Title: " + title + " | Price: "
                + (isFree() ? "Free" : "$" + String.format("%.2f", price)) + " ]" ;
    }

    public boolean isFree(){
        return this.price == 0.0;
    }

    public List<Lesson> getLessons() {
        return Collections.unmodifiableList(lessons);
    }

    public void addLesson(Lesson lesson){
        this.lessons.add(lesson);
    }

    public void deleteLesson(Lesson lesson){
        this.lessons.remove(lesson);
    }

    public List<Assignment> getAssignments(){
        return Collections.unmodifiableList(assignments);
    }

    public List<Student> getEnrolledStudents() {
        return Collections.unmodifiableList(enrolledStudents);
    }

    public void addEnrolledStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }

    public void removeEnrolledStudent(Student student) {
        enrolledStudents.remove(student);
    }

    public void addAssignment(Assignment assignment){
        this.assignments.add(assignment);
    }

    public boolean removeAssignment(Assignment assignment) {
        return this.assignments.remove(assignment);
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

}