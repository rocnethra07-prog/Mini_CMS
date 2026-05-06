package course_manager_v1.model;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class AssignmentProgress {
    private final Map<String, Integer> assignmentScores = new HashMap<>();

    public void submitAssignment(Assignment assignment, int score){
        if(score < 0){
            throw new IllegalArgumentException("Score cannot be negative");
        }
        assignmentScores.put(assignment.getId(), score);
    }

    public Integer getScore(Assignment assignment){
        return assignmentScores.get(assignment.getId());
    }

    public boolean isSubmitted(Assignment assignment){
        return assignmentScores.containsKey(assignment.getId());
    }

    public int getAttemptedCount(){
        return assignmentScores.size();
    }

    public double getAverageScore(){
        if(assignmentScores.isEmpty()) return 0;

        int total = assignmentScores.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        return total * 1.0 / assignmentScores.size();
    }

    public Map<String, Integer> getAssignmentScores(){
        return Collections.unmodifiableMap(assignmentScores);
    }
}
