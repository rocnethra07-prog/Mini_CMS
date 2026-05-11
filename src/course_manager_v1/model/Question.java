package course_manager_v1.model;

import java.util.Collections;
import java.util.List;

public class Question {
    private String questionText;
    private List<String> options;
    private int correctOptIndex;
    private int mark;

    public Question(String questionText, List<String> options ,int correctOptIndex, int mark){
        if(questionText == null || questionText.isBlank()){
            throw new IllegalArgumentException("Question cannot be empty");
        }

        if(options == null || options.size() < 2){
            throw new IllegalArgumentException("At least 2 options required");
        }

        for(String option : options){
            if(option == null || option.isBlank()){
                throw new IllegalArgumentException("Option cannot be empty");
            }
        }

        if(correctOptIndex < 0 || correctOptIndex >= options.size()){
            throw new IllegalArgumentException("Invalid correct option index");
        }

        if(mark <= 0){
            throw new IllegalArgumentException("Mark must be positive");
        }
        this.questionText = questionText;
        this.options = options;
        this.correctOptIndex = correctOptIndex;
        this.mark = mark;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return Collections.unmodifiableList(options);
    }

    public int getCorrectOptIndex() {
        return correctOptIndex;
    }

    public int getMark() {
        return mark;
    }

}
