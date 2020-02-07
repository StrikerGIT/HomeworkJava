package com.geekbrains.geek.cloud.client;

import com.geekbrains.geek.cloud.common.CloudPackage;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class MainClient {
    public static void main(String[] args) {
        bytesClientExample();
    }

    private static void bytesClientExample() {
        try (Socket socket = new Socket("localhost", 8189)) {
            BufferedOutputStream  bos = new BufferedOutputStream(socket.getOutputStream());
            bos.write(15); // кодовый символ передачи файла
            String filename = "1.txt";
            int filenameLength = filename.length();
            bos.write(filenameLength); // длина имени
            bos.write(filename.getBytes()); // имя файла
            byte[] bytesFromFile = Files.readAllBytes(Paths.get("1.txt")); // преобразуем файл в байтовый массив
            bos.write(bytesFromFile); // передаём байтовый массив

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}