package course_manager_v1.model;

import java.util.List;

public class Question {
    private String questionText;
    private List<String> options;
    private int correctOptIndex;
    private int mark;

    public Question(String questionText, List<String> options ,int correctOptIndex, int mark){
        this.questionText = questionText;
        this.options = options;
        this.correctOptIndex = correctOptIndex;
        this.mark = mark;
    }

    public String getQuestionText(){
        return questionText;
    }

}
