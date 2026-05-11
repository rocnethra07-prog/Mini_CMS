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

}
