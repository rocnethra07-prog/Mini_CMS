package course_manager_v1.util;

public class IdGenerator {
    private static int user = 1;
    private static int course = 1;
    private static int lesson = 1;
    private static int resource = 1;

    public static String generateUserId(){
        return "USR-" +user++;
    }
    public static String generateCourseId(){
        return "CRS-" + course++;
    }
    public static String generateLessonId(){
        return "LSN-"+lesson++;
    }
    public static String generateResourceId(){
        return "RES-" + resource++;
    }
}
