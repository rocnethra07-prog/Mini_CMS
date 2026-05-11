package course_manager_v1.util;

import java.util.Scanner;
import java.util.function.Predicate;

public class InputUtil {
    private static String getValidatedInput(Scanner sc, String prompt, Predicate<String> validator, String errorMessage){
        while(true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if(validator.test(input)){
                return input;
            }
            System.out.println(errorMessage);
        }
    }

    public static String getTitle(Scanner sc, String prompt, String errorMessage){
        return getValidatedInput(sc,prompt, Validator::isValidTitle , errorMessage);
    }

    public static double getPrice(Scanner sc, String prompt, String errorMessage) {
        String input = getValidatedInput(sc, prompt, Validator::isValidPrice, errorMessage);
        return Double.parseDouble(input);
    }

    public static String getNonEmptyInput(Scanner sc, String prompt, String errorMessage) {
        return getValidatedInput(sc, prompt, input -> !input.isEmpty(), errorMessage);
    }

}