package Lesson_3;

import java.util.*;

public class MainCollection {
    public static void main(String[] args) {
        String[] arr = {"апельсин","лимон","яблоко","банан","грейпфрут","лимон","яблоко", "лимон","персик","апельсин"};
        System.out.println(Arrays.toString(arr));

            HashMap<String, Integer> fruitPacket = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            Integer number = fruitPacket.get(arr[i]);
            fruitPacket.put(arr[i], number == null ? 1 : number + 1);
       }

        System.out.println(fruitPacket);
    }
}
