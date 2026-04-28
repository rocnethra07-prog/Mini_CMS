package course_manager_v2.util;

public class IdGenerator {
    private static int user = 1;
    public static String generateUserId(){
        return "USR-" +user++;
    }
}
