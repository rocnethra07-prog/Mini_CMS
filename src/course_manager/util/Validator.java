package course_manager.util;

import java.net.URL;

public class Validator {

    public static boolean isValidName(String name){
        return name != null && !name.trim().isEmpty() && name.length() >= 3;
    }

    public static boolean isValidEmail(String email){
        return email != null && email.contains("@") && email.contains(".");
    }

    public static boolean isValidPassword(String password){
        return password != null && password.length() >= 6;
    }

    public static boolean isValidTitle(String title){
        return title != null && !title.trim().isEmpty() && title.length() >= 3;
    }

    public static boolean isValidPrice(String price){
        try {
            double val = Double.parseDouble(price);
            return val >= 0;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidURL(String url){
        try{
            new URL(url).toURI();

            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public static boolean isValidLink(String url){
        return isValidURL(url);
    }

    public static boolean isValidVideo(String url){
        if(!isValidURL(url)) return false;

        String lower = url.toLowerCase();
        return lower.contains("youtube.com") || lower.contains("youtu.be");
    }

    public static boolean isValidPDF(String url){
        if(!isValidURL(url)) return false;

        return url.toLowerCase().endsWith(".pdf");
    }


}