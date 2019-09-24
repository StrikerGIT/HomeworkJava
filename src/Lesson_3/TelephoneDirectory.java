package Lesson_3;

import java.util.HashMap;
import java.util.HashSet;

public class TelephoneDirectory {
   public  HashMap <String, HashSet<String>> td;

    TelephoneDirectory() {
        td = new HashMap<String, HashSet<String>>();
        }

    public void add(String login,String ... tNumber){
        if (!td.containsKey(login)) {
            td.put(login,new HashSet<String>());
        }
        for (String number:tNumber) {
            td.get(login).add(number);
        }
    }

    public void get(String login){
        HashSet setNumber = td.get(login);
        if (setNumber!=null){
            System.out.println(setNumber);
        }
        else {
            System.out.println("Пользователь с логином: " + login + " не обнаружен.");
        }
    }

    public static void main(String[] args) {
        TelephoneDirectory telephoneDirectory = new TelephoneDirectory();
        System.out.println("Работаем с телефонным справочником");
        System.out.println("__________________________________");
        telephoneDirectory.add("Вася","+79246341534","+75236351578","+72246341555");
        telephoneDirectory.add("Коля","+79999999999","+75555555555","+72222222222","+71111111111");

        telephoneDirectory.get("Вася");
        telephoneDirectory.get("Петя");
        telephoneDirectory.get("Коля");
        System.out.println("__________________________________");
    }

}
