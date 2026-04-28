package course_manager.model;

import course_manager.util.IdGenerator;

public class Resource {
    private String id;
    private String url;
    private String type;

    public Resource(String url, String type){
        this.url = url;
        this.type=type;
        this.id = IdGenerator.generateResourceId();
    }

    public String getId(){
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString(){
        return id + " [" + type + "] -> " + url;
    }

}
