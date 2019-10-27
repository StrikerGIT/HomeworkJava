package Lesson_3;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        Server server = new Server();
    }

    public Server() {
        ServerSocket server = null;
        Socket socket = null;
        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен. Ожидаем клиентов...");
                socket = server.accept();
                System.out.println("Клиент подключился");


            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            MyObject mo = (MyObject)ois.readObject();
            System.out.println("А вот такой объект мы восстановили из набора байтов: " + mo);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
