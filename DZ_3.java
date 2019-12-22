package lesson3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class DZ_3 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        //Стек
        System.out.println("Создаём стек. Введите желаемое количество элементов.");
        int size =  in.nextInt();

        System.out.println("Создаётся стек размерностью " + size);
        MyStack stack = new MyStack(size);
        System.out.println("Введите " + size + " целочисленных элементов");
        for (int i = 0; i < size; i++) {
            stack.push(in.nextInt());
        }

        System.out.println("Теперь наш стек выглядит так:");
        for (int i = 0; i < size; i++) {
            System.out.print(stack.pop());
        }
        System.out.println();

        //очередь
        System.out.println("Создаём очередь. Введите желаемое количество элементов.");
        size =  in.nextInt();

        System.out.println("Создаётся очередь размерностью " + size);
        MyQueue queue = new MyQueue(size);
        System.out.println("Введите " + size + " целочисленных элементов");
        for (int i = 0; i < size; i++) {
            queue.insert(in.nextInt());
        }

        System.out.println("Теперь наша очередь выглядит так:");
        for (int i = 0; i < size; i++) {
            System.out.print(queue.remove());
        }
        System.out.println();

        //очередь с приоритетом
        System.out.println("Создаём очередь с приоритетом. Введите желаемое количество элементов.");
        size =  in.nextInt();

        System.out.println("Создаётся очередь с приоритетом размерностью " + size);
        MyPriorityQueue priorityQueue = new MyPriorityQueue(size);
        System.out.println("Введите " + size + " целочисленных элементов");
        for (int i = 0; i < size; i++) {
            priorityQueue.insert(in.nextInt());
        }

        System.out.println("Теперь наша очередь с приоритетом выглядит так:");
        for (int i = 0; i < size; i++) {
            System.out.print(priorityQueue.remove());
        }
//        System.out.println();

        //Очередь доступная с двух сторон для добавления
        System.out.println("Создаём двойную очередь. Введите желаемое количество элементов.");
        size =  in.nextInt();

        System.out.println("Создаётся двойная очередь размерностью " + size);
        MyDeQueue dequeue = new MyDeQueue();
        System.out.println("Введите " + size + " целочисленных элементов");
        int nextNumber;
        for (int i = 0; i < size; i++) {
            nextNumber = in.nextInt();
            if ((nextNumber % 2) == 0){
                dequeue.insertLast(nextNumber);
            }else {
                dequeue.insertFirst(nextNumber);
            }
        }

        System.out.println("Теперь наша двойная очередь выглядит так:");
        for (int i = 0; i < size; i++) {
            System.out.print(dequeue.removeFirst());
        }
        System.out.println();



        // дальше либо вывести массив в обратном порядке, либо записать в стек и из стека прочитать.
        //но судя по формулировке задания я так понял надо сразу читать по 1 символу в стек, но это как-то неудобно
        //вводить по 1 символу с консоли.
        // Подскажите, как надо было читать введённую с консоли строку посимвольно? Через байты?

        System.out.println("Введите строку. Она будет воспроизведена в обратном порядке");
        String text;
        text =  in.next();
        char[] strToArray = text.toCharArray();
        for (int i = strToArray.length-1; i >= 0; i--) {
            System.out.print(strToArray[i]);
        }
        System.out.println();

    }
}
