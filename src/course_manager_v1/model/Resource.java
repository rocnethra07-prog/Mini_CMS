package course_manager_v1.model;

import course_manager_v1.util.IdGenerator;

public class Resource {
    private String id;
    private String url;
    private ResourceType type;

    public Resource(String url, ResourceType type){

        if(url == null || url.isBlank()){
            throw new IllegalArgumentException("URL cannot be empty");
        }

        if(type == null){
            throw new IllegalArgumentException("Resource type cannot be null");
        }

        this.url = url;
        this.type=type;
        this.id = IdGenerator.generateResourceId();
    }

    public String toString(){
        return id + " [" + type + "] -> " + url;
    }

    public String getId(){
        return id;
    }

}
