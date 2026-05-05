package course_manager_v1.util;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

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

    public static boolean isValidPrice(String price){
        try {
            double val = Double.parseDouble(price);
            return val >= 0;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidURL(String url) {
        try{
            URL u = new URL(url);
            u.toURI();

            String protocol = u.getProtocol();
            return protocol.equalsIgnoreCase("http") || protocol.equalsIgnoreCase("https");
        }
        catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }

    public static boolean isValidLink(String url){
        return isValidURL(url);
    }

    public static boolean isValidVideo(String url){
        if(!isValidURL(url)) return false;
        return url.contains("youtube.com") || url.contains("youtu.be") || url.contains("vimeo.com");
    }


    public static boolean isValidPDF(String url){
        if(!isValidURL(url)) return false;

        return url.endsWith(".pdf");
    }

    public static boolean isValidImage(String url){
        if(!isValidURL(url)) return false;

        return url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith("png") || url.endsWith(".webp") || url.endsWith(".gif");
    }


}