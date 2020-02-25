package GeekCloud;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;

public class ProtoClient {


    public static void main(String[] args) throws Exception {

        CountDownLatch networkStarter = new CountDownLatch(1);
        new Thread(() -> Network.getInstance().start(networkStarter)).start();
        networkStarter.await();

        // отправляем файл

        ProtoFileSender.sendFile(Paths.get("1.txt"), Network.getInstance().getCurrentChannel(), future -> {
            if (!future.isSuccess()) {
                future.cause().printStackTrace();
                Network.getInstance().stop();
            }
            if (future.isSuccess()) {
                System.out.println("Файл успешно передан на сервер");
                //Network.getInstance().stop();
            }
        });


        Thread.sleep(3000);

        // скачиваем файл
        ProtoFileSender.getFile(Paths.get("1.txt"), Network.getInstance().getCurrentChannel(), future -> {
            if (!future.isSuccess()) {
                future.cause().printStackTrace();
                Network.getInstance().stop();
            }
            if (future.isSuccess()) {
                System.out.println("Файл успешно передан на сервер");
                //Network.getInstance().stop();
            }
        });

        Thread.sleep(3000);
        Network.getInstance().stop();
    }
}
