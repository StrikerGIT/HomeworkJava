package Lesson_3;

import java.io.Serializable;

public class MyObject implements Serializable {

    int size;
    int weight;


    public MyObject(int size, int weight) {
        this.size = size;
        this.weight = weight;
    }
    @Override
    public String toString() {
        return "Мой объект с характеристиками: Размер =  " + size + ", Вес = " + weight + ".";
    }

}
