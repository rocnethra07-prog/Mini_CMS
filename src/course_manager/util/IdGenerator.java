package course_manager.util;

public class IdGenerator {
    private static int user = 1;
    private static int course = 1;
    public static String generateUserId(){
        return "USR-" +user++;
    }

    public static String generateCourseId(){
        return "CRS-"+ course++;
    }
}
