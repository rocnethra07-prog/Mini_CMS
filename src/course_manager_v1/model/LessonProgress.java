package course_manager_v1.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LessonProgress {
    private final Set<String> completedLessonIds = new HashSet<>();

    public boolean markCompleted(Lesson lesson){
        return completedLessonIds.add(lesson.getId());
    }

    public boolean isCompleted(Lesson lesson){
        return completedLessonIds.contains(lesson.getId());
    }

    public int getCompletedCount(){
        return completedLessonIds.size();
    }

    public double getCompletionPercentage(Course course){
        int total = course.getLessons().size();
        if(total == 0) return 0;
        return (completedLessonIds.size() * 100.0) / total;
    }

    public Set<String> getCompletedLessonIds(){
        return Collections.unmodifiableSet(completedLessonIds);
    }
}
