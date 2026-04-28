package course_manager_v2.util;

public class Validator {

    private static boolean isNullOrEmpty(String s){
        return s == null || s.trim().isEmpty();
    }
    public static boolean isValidName(String name){
        return !isNullOrEmpty(name) && name.trim().length() >= 3;
    }

    public static boolean isValidEmail(String email){
        if(isNullOrEmpty(email)) {
            return false;
        }
        email = email.trim();
        return email.contains("@") && email.contains(".") &&
                email.indexOf("@") > 0 &&
                email.indexOf("@") < email.lastIndexOf(".") &&
                email.indexOf("@") == email.lastIndexOf("@");
    }

    public static boolean isValidPassword(String password){
        return !isNullOrEmpty(password) && password.trim().length() >= 6;
    }

    public static boolean isValidTitle(String title){
        return !isNullOrEmpty(title) && title.trim().length() >= 3;
    }


}