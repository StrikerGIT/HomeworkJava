package lesson2;

import java.util.Comparator;
import java.util.Random;

public class DZ_2 {
    public static Random rand = new Random();

    public static void main(String[] args) {

        MyArrayList<Integer> mal = inicializeArray();
        Long startTime = System.currentTimeMillis();

        mal.bubbleSort();
        System.out.println(System.currentTimeMillis() - startTime);
        //2389743    в 10,9 раза дольше

        mal = inicializeArray();
        startTime = System.currentTimeMillis();
        mal.selectionSort();
        System.out.println(System.currentTimeMillis() - startTime);
        //571864    в 2,6 раза дольше

        mal = inicializeArray();
        startTime = System.currentTimeMillis();
        mal.insertionSort();
        System.out.println(System.currentTimeMillis() - startTime);
        //218815   самый быстрый


    }

    public static MyArrayList<Integer> inicializeArray(){
        MyArrayList<Integer> mal = new MyArrayList<>(1000000);
        for (int i = 0; i < 1000000 ; i++) {
            mal.add(rand.nextInt(100));
//                if (i%10000==0){
//                    System.out.println(i);
//                }
        }
       return  mal;
    }

}
