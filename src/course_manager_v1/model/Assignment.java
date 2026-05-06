package course_manager_v1.model;


import course_manager_v1.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

//assignment or quiz
public class Assignment {
    private String id;
    private String title;
    private List<Question> questionsList ;

    public Assignment(String title){
        this.title = title;
        this.questionsList = new ArrayList<>();
        this.id = IdGenerator.generateAssignmentId();
    }

    public String getId() {
        return id;
    }

    public void addQuestion(Question question){
        this.questionsList.add(question);
    }

    public List<Question> getQuestionsList(){
        return new ArrayList<>(questionsList);
    }
    public String toString(){
        return "[ ID : "+ id + " | Title : " + this.title + " ]" ;
    }
}
