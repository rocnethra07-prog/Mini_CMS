package course_manager.util;

public class IdGenerator {
    private static int user = 1;
    public static String generateUserId(){
        return "USR-" +user++;
    }

}
