package Lesson_6;

public class HW_MyClass {

    public int[] repack(int[] myArray) {
        int[] repackArray = {0};
        int lengthArray = myArray.length;
        boolean flagFind = false;

        for (int i = lengthArray; i >= 0; i--) {
            if (myArray[i-1] == 4){
                repackArray = new int[lengthArray - i];
                System.arraycopy(myArray, i, repackArray, 0, lengthArray - i);
                flagFind = true;
                break;
            }
        }
        if (!flagFind){
            throw new RuntimeException();
        }
        return repackArray;
    }

    public boolean findNum(int[] myArray) {
        boolean flagFind = false;

        for (int elem: myArray) {
            if (elem == 1 || elem == 4){
                flagFind = true;
                break;
            }
        }
        return flagFind;
    }
}
