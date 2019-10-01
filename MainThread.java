package Lesson_5;
import java.util.Arrays;
import java.util.ArrayList;


public class MainThread {
    static final int size = 10000000;
    static final int countOfThreads = 8;
    static final int h = size / countOfThreads;
    static ArrayList<float[]> arrLOfParts = new ArrayList<float[]>();
    static ArrayList<Thread> arrLOfThreads =  new ArrayList<Thread>();


    public static void main(String[] args) throws InterruptedException {
        float[] arr = new float[size];
        Arrays.fill(arr, 1);
        long time = System.currentTimeMillis();

        //Первый метод
        for (int i=0;i<arr.length;i++ ){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("При однопоточной работе ушло времени: " + (System.currentTimeMillis() - time));

        //Второй метод
        Arrays.fill(arr, 1);
        time = System.currentTimeMillis();
        //Создадим нужное количество массивов = количеству потоков
        createArrays();
        //Разделим массивы
        devideArrays(arr);
        System.out.println("Разделили массив на " + countOfThreads + " частей: " + (System.currentTimeMillis() - time));
        //Создадим потоки и запустим вычисления
        calculate(arr);
        //дождёмся завершения
        try {
            for (Thread thread : arrLOfThreads){
                thread.join();
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Завершили расчёт. Приступаем к склеиванию: " + (System.currentTimeMillis() - time));
        //Склеим массивы
        stack(arr);

        System.out.println("При работе в " + countOfThreads + " потоков ушло времени: " + (System.currentTimeMillis() - time));

    }
    public static void createArrays() {
        for (int i=0;i<countOfThreads;i++){
            arrLOfParts.add(new float[h]);
        }
    }
    public static void devideArrays(float[] arr) {
        for (int i=0;i<countOfThreads;i++){
            float[] curentArr = arrLOfParts.get(i);
            System.arraycopy(arr, i * (h), curentArr, 0, h);
        }
    }
    public static void stack(float[] arr) {
        for (int i=0;i<countOfThreads;i++){
            float[] curentArr = arrLOfParts.get(i);
            System.arraycopy(curentArr, 0, arr, i * (h), h);
        }
    }
    public static void calculate(float[] arr) {
        for (float[] elemArr : arrLOfParts){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < elemArr.length; j++) {
                        elemArr[j] = (float)(elemArr[j] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
                    }
                }
            });
            t.start();
            arrLOfThreads.add(t);
        }
    }
}
