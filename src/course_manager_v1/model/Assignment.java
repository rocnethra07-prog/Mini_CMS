package course_manager_v1.model;

import course_manager_v1.util.IdGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//assignment or quiz
public class Assignment {
    private String id;
    private String title;
    private final List<Question> questionsList = new ArrayList<>() ;

    public Assignment(String title){
        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
        this.id = IdGenerator.generateAssignmentId();
    }

    public String getId() {
        return id;
    }

    public void addQuestion(Question question){
        this.questionsList.add(question);
    }

    public List<Question> getQuestionsList(){
        return Collections.unmodifiableList(questionsList);
    }

    public String toString(){
        return "[ ID : "+ id + " | Title : " + this.title + " ]" ;
    }

}