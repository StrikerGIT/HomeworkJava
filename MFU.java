package Lesson_4;

import java.util.concurrent.*;

import static java.lang.Math.sqrt;

public class MFU {

    public static void main(String[] args) {

        ExecutorService executorServicePrint = Executors.newSingleThreadExecutor();
        ExecutorService executorServiceScan = Executors.newSingleThreadExecutor();
        executorServicePrint.execute(new MyRunnablePrint());
        executorServiceScan.execute(new MyRunnableScan());
        executorServicePrint.execute(new MyRunnablePrint());
        executorServiceScan.execute(new MyRunnableScan());
        executorServicePrint.execute(new MyRunnablePrint());
        executorServiceScan.execute(new MyRunnableScan());
        executorServicePrint.execute(new MyRunnablePrint());
        executorServiceScan.execute(new MyRunnableScan());
        executorServicePrint.execute(new MyRunnablePrint());
        executorServiceScan.execute(new MyRunnableScan());
        executorServicePrint.execute(new MyRunnablePrint());
        executorServiceScan.execute(new MyRunnableScan());
        executorServicePrint.execute(new MyRunnablePrint());
        executorServiceScan.execute(new MyRunnableScan());
        executorServicePrint.execute(new MyRunnablePrint());
        executorServiceScan.execute(new MyRunnableScan());
        executorServicePrint.execute(new MyRunnablePrint());
        executorServiceScan.execute(new MyRunnableScan());

        executorServicePrint.shutdown();
        executorServiceScan.shutdown();
    }

    public static class MyRunnablePrint implements Runnable {
        @Override
        public void run() {
            System.out.println("печатаем...");
            double d = 0;
            for (int i = 0; i < 1000000000; i++) {
                d = sqrt(427827856 * i);
            }
            System.out.println("печать выполнена");
        }
    }
    public static class MyRunnableScan implements Runnable {
        @Override
        public void run() {
            System.out.println("сканируем...");
            double d = 0;
            for (int i = 0; i < 1000000000; i++) {
                d = sqrt(427827856 * i);
            }
            System.out.println("сканирование выполнено");
        }
    }

}