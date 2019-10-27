package Lesson_3;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        getInputStream();
    }

    public static void  getInputStream(){
                DataOutputStream out;

        final String IP_ADDRESS = "localhost";
        final int PORT = 8189;
        try {
            Socket socket = new Socket(IP_ADDRESS, PORT);
            ObjectOutputStream serializer = new ObjectOutputStream(socket.getOutputStream());
            MyObject myObjOut = new MyObject(10,20);
            serializer.writeObject(myObjOut);
            serializer.flush();
            //serializer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
