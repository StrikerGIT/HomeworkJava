package GeekCloud;

import GeekCloud.Proto.Network;
import GeekCloud.Proto.ProtoFileSender;
import GeekCloud.Proto.ProtoServer;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;

import java.io.IOException;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;

public class Client {
    public static void main(String[] args) {
        ObjectEncoderOutputStream oeos = null;
        ObjectDecoderInputStream odis = null;

        System.out.println("Start connecting!");

        try (Socket socket = new Socket("localhost", 8181)) {
            //пробуем подключиться к серверу и пройти авторизацию
            oeos = new ObjectEncoderOutputStream(socket.getOutputStream());
            MyMessage textMessage = new MyMessage("/Connect", MessageType.COMMAND);
            oeos.writeObject(textMessage);
            oeos.flush();
            // ждём уведомление об успешном подключении
            odis = new ObjectDecoderInputStream(socket.getInputStream(), 100 * 1024 * 1024);
            MyMessage msgFromServer = (MyMessage)odis.readObject();
            System.out.println("Answer from server: " + msgFromServer.getText());

            // Если всё успешно, то отправляем сообщение, что будем передавать файл
            textMessage = new MyMessage("/Upload 1.txt", MessageType.COMMAND);
            oeos.writeObject(textMessage);
            oeos.flush();

            //запускаем протокольную отправку файла
            CountDownLatch networkStarter = new CountDownLatch(1); // замок с обратным отсчетом, для блокировки
           // new Thread(() -> Network.getInstance().start(networkStarter)).start(); // Стартуем поток Network, в замок + 1
           // Network MyNetwork =  Network.getInstance();
            new Thread(() -> Network.getInstance().start(networkStarter)).start();
            networkStarter.await(); // замок ожидает выполнения Network




            ProtoFileSender.sendFile(Paths.get("1.txt"), Network.getInstance().getCurrentChannel(), future -> { //отправка файла. future - выход нашего finishListener
                if (!future.isSuccess()) {
                    future.cause().printStackTrace(); // если ошибка, то выведем описание
                    Network.getInstance().stop();  // закрываем
                }
                if (future.isSuccess()) {
                    System.out.println("Файл успешно передан");
                    Network.getInstance().stop();  // закрываем
                }
            });
            //***********************************
//            // ждём уведомление об успехе передачи файла
//            odis = new ObjectDecoderInputStream(socket.getInputStream(), 100 * 1024 * 1024);
//            msgFromServer = (MyMessage)odis.readObject();
//            System.out.println("Answer from server: " + msgFromServer.getText());

            //Теперь скачиваем файл с сервера
//            textMessage = new MyMessage("/Download 21.txt", MessageType.COMMAND);
//            oeos.writeObject(textMessage);
//            oeos.flush();
//
//            new Thread(() -> {
//                try {
//                    new ProtoServer().run(); // стартуем поток для приёма файла
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oeos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                odis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
