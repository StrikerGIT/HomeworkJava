package Lesson_2;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import jdk.nashorn.internal.runtime.NumberToString;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainEx {
    public static void main(String[] args) {

        String [][] stringArray1 = new String [4][4];   //неинициализованный массив
        String [][] stringArray2= {{"1","2","3","4"}, {"2","6","7","8"}, {"9","0","1","2"}, {"3","4","5","6"}}; //корректный массив
        String [][] stringArray3 ={{"1","2","а","4"}, {" ","6","7","8"}, {"9","","1","2"}, {"3","4","5","6"}};  // массив со значениями вызывающими исключение

        System.out.println("_______________________________________");
        challenge(stringArray1);    // ошибка инциализации
        System.out.println("_______________________________________");
        challenge(stringArray2);    // корректная работа
        System.out.println("_______________________________________");
        challenge(stringArray3);    // ошибка преобразования в число
        System.out.println("_______________________________________");
    }

    public static void challenge(String[][] myarray) {
        try { checksize(myarray);
            arraySum(myarray);
        }

        catch (MyArraySizeException e) {
            e.printStackTrace();
            System.out.println("Исправьте размерность массива!");
        }
        catch (MyArrayDataException e) {
            e.printStackTrace();
            System.out.println("Исправьте ошибку!");
        }
    }

    public static void checksize(String[][] myarray) throws  MyArraySizeException{
        int rows = myarray.length;
        int columns = myarray[0].length;

        if (rows != 4 || columns != 4) {
            throw new  MyArraySizeException("Наш массив должен иметь размер 4x4. Текущий размер массива " + rows + "x" + columns );
        }
    }

    public static void arraySum(String [][] inArray) throws MyArrayDataException{
        int sum = 0;
        String message = "";
            for (int i=0; i < inArray.length; i++){
                for (int j=0; j < inArray[i].length; j++) {
                    try {
                        sum += Integer.parseInt(inArray[i][j]);
                    } catch (NumberFormatException e){
                        if (inArray[i][j] == null) {
                           message = "Массив не определён. Нужно инициализировать массив";
                        }
                        else {
                            message = "В элементе [" + i + "][" + j + "] + содержится значение " + inArray[i][j] + ", не являющееся числом.";
                        }
                        throw new MyArrayDataException(message);
                    }
                }
            }
            System.out.println("Вычисления выполнены корректно. Сумма массива = " + sum);
        }

    }

