package com.geekbrains.geek.cloud.server;

import com.geekbrains.geek.cloud.common.CloudPackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.Arrays;

public class MainServer {
    public static void main(String[] args) {
        bytesServerExample();
    }

    private static void bytesServerExample() {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен. Ожидаем подключение клиента");
            try (Socket socket = serverSocket.accept();

                // ByteArrayInputStream bIn = new ByteArrayInputStream();

                 BufferedInputStream in = new BufferedInputStream(socket.getInputStream())) {
                System.out.println("Клиент подключился");
                int n;
                byte[] byteArray = new byte[8192];
                while ((n = in.read()) != -1) {
                    if (n==15){ //15 значит, что получаем файл
                        n = in.read(); // получаем длину имени
                        // далее получаем имя файла
                        // далее получаем побитово файл
                        in.read(byteArray); // получаем массив бит файла
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
    }
}
}

