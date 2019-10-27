package Lesson_3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class HomeWork  {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        readFile("123/HW_test1.txt");
        readSameFile("123/HW_test1.txt","123/HW_test2.txt","123/HW_test3.txt","123/HW_test4.txt","123/HW_test5.txt");
        pageReading( "123/HW_bigFile.txt",1800);
        reverseRead("123/HW_test1.txt");

    }

    public static void readFile(String nameFile){
        try (FileInputStream in = new FileInputStream(nameFile)) {
            int x;
            byte[] arr = new byte[100];

            while ((x = in.read(arr)) != -1) {
                System.out.println(new String(arr,0,x, "UTF-8"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void pageReading(String nameFile, int size){
        try (InputStream in = new BufferedInputStream(new FileInputStream(nameFile),size)) {
            int x;
            byte[] arr = new byte[size];
            int i = 1;
            while ((x = in.read(arr,0,size)) != -1) {
                System.out.println("Page " + i + "\n");
                System.out.println(new String(arr,0,x, "UTF-8"));
                System.out.println("\n");
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readSameFile (String ... files) throws IOException, ClassNotFoundException{
        int x;
        ArrayList<InputStream> ali = new ArrayList<>();
        for (String file:files) {
            ali.add(new FileInputStream(file));
        }

        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(ali));

        while ((x = in.read()) != -1) {
            System.out.print((char)x);
        }

        in.close();
    }
    public static void reverseRead (String name) throws IOException, ClassNotFoundException{
        ReverseLineReader  reader = new ReverseLineReader (new File(name),"UTF-8");

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }


}
