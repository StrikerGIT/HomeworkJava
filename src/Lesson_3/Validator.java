package Lesson_3;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$)";

    private final Pattern pattern;

    private Matcher matcher;

    public Validator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public boolean validate(String password) {
    matcher = pattern.matcher(password);
    return matcher.matches();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите пароль:");

        String password = in.nextLine();
        in.close();

        //проверим пароль на валидность
        Validator Valid = new Validator();

        System.out.println(Valid.validate(password)?"пароль корректный":"пароль не отвечает требованиям безопасности");
    }
}


