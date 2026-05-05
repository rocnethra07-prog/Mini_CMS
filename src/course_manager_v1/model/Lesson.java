package course_manager_v1.model;

import java.util.ArrayList;
import java.util.List;
import course_manager_v1.util.IdGenerator;

public class Lesson {
    private String id;
    private String title;
    private String content;
    private List<Resource> resources;

    public Lesson(String title, String content, List<Resource> resources ){
        this.title = title;
        this.content = content;
        this.id = IdGenerator.generateLessonId();
        this.resources = resources != null ? resources : new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Resource> getResourceList() {
        return resources;
    }

    public void addResource(Resource r){
        resources.add(r);
    }

    public boolean removeResource(String resourceId){
        return resources.removeIf(r -> r.getId().equals(resourceId));
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return "Lesson ID: " + id +
                " | Title: " + title +
                " | Content: " + content +
                " | Resources count: " + resources.size() + "\n";
    }
}
