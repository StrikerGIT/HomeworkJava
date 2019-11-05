package Lesson_6;

public class HomeWork {

    private static Object RuntimeException;

    public static void main(String[] args) {
        HW_MyClass myClass = new HW_MyClass();

        int[] myArray = {1,2,5,3,6,4,7,5,3,6,4,3,5};
        int[] repackArray = myClass.repack(myArray);
        for (int elem: repackArray) {
            System.out.println(elem);
        }

        if (myClass.findNum(myArray)){
            System.out.println("в массиве есть нужные нам числа");
        }
        else {
            System.out.println("нужные числа в массиве не найдены!");
        }
    }

}
