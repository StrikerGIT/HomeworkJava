package Lesson_6.Server;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Vector;

public class MainServ {
    ServerSocket server = null;
    Socket socket = null;

    public MainServ() {
        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

                socket = server.accept();
                System.out.println("Клиент подключился!");

            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner console = new Scanner(System.in);

             //Теперь запускам поток на работу сервера
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            String str = in.nextLine();
                            if (str.equals("/end")) {
                                out.println("/end");
                                break;
                            }
                            System.out.println("Client " + str);
                        }
                    }
                });
                t1.setDaemon(true);
                t1.start();

                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            System.out.println("Введите сообщение");
                            String str = console.nextLine();
                            System.out.println("Сообщение отправлено!");
                            out.println(str);
                        }
                    }
                });
                t2.setDaemon(true);
                t2.start();

                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
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
