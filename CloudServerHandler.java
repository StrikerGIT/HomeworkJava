package GeekCloud;

import GeekCloud.Proto.Network;
import GeekCloud.Proto.ProtoFileSender;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;

public class CloudServerHandler extends ChannelInboundHandlerAdapter {

    public enum State {
        IDLE, NAME_LENGTH, NAME, FILE_LENGTH, FILE, OFF
    }

    private State currentState = State.OFF;
    private int nextLength;
    private long fileLength;
    private long receivedFileLength;
    private BufferedOutputStream out;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client connected...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (msg == null) {
                return;
            }
            //**************************
            if (currentState != State.OFF) {
                ByteBuf buf = ((ByteBuf) msg);
                if (currentState == CloudServerHandler.State.IDLE) {
                    byte readed = buf.readByte();
                    if (readed == (byte) 25) {
                        currentState = State.NAME_LENGTH;
                        System.out.println("STATE: Start file receiving");
                    } else {
                        System.out.println("ERROR: Invalid first byte - " + readed);
                    }
                }

                if (currentState == State.NAME_LENGTH) {
                    if (buf.readableBytes() >= 4) {
                        System.out.println("STATE: Get filename length");
                        nextLength = buf.readInt();
                        currentState = State.NAME;
                    }
                }

                if (currentState == State.NAME) {
                    if (buf.readableBytes() >= nextLength) {
                        byte[] fileName = new byte[nextLength];
                        buf.readBytes(fileName);
                        System.out.println("STATE: Filename received - _" + new String(fileName));
                        out = new BufferedOutputStream(new FileOutputStream("_" + new String(fileName)));
                        currentState = State.FILE_LENGTH;
                    }
                }

                if (currentState == State.FILE_LENGTH) {
                    if (buf.readableBytes() >= 8) {
                        fileLength = buf.readLong();
                        System.out.println("STATE: File length received - " + fileLength);
                        currentState = State.FILE;
                    }
                }

                if (currentState == State.FILE) {
                    while (buf.readableBytes() > 0) {
                        out.write(buf.readByte());
                        receivedFileLength++;
                        if (fileLength == receivedFileLength) {
                            currentState = State.OFF;
                            System.out.println("File received");
                            out.close();
                        }
                    }
                }

                buf.release();
            }
            else {
                //**************************
                if (msg instanceof MyMessage) {
                    if (((MyMessage) msg).getMessageType() == MessageType.COMMAND) {// если команда
                        String[] comandParts = ((MyMessage) msg).getText().split(" ", 2);

                        if (comandParts[0].equalsIgnoreCase("/Connect")) {// пришла команда на авторизацию, то проводим авторизацию
                            // здесь будет авторизация
                            ctx.writeAndFlush(new MyMessage("connection successful...", MessageType.TEXT));
                        } else if (comandParts[0].equalsIgnoreCase("/Upload")) { // команда загрузки файла на сервер
                            currentState = CloudServerHandler.State.IDLE;
//                        new Thread(() -> {
//                            try {
//                                new ProtoServer().run(); // стартуем поток для приёма файла
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }).start();
//                        ctx.writeAndFlush(new MyMessage("file loading...", MessageType.TEXT));
                        } else if (comandParts[0].equalsIgnoreCase("/Download")) { // выгружаем файл с сервера клиенту
                            //Перед выгрузкой надо проверить, есть ли у нас такой файл
                            //запускаем протокольную отправку файла
                            CountDownLatch networkStarter = new CountDownLatch(1); // замок с обратным отсчетом, для блокировки
                            new Thread(() -> Network.getInstance().start(networkStarter)).start(); // Стартуем поток Network, в замок + 1
                            networkStarter.await(); // замок ожидает выполнения Network

                            ProtoFileSender.sendFile(Paths.get(comandParts[1]), Network.getInstance().getCurrentChannel(), future -> { //отправка файла. future - выход нашего finishListener
                                if (!future.isSuccess()) {
                                    future.cause().printStackTrace(); // если ошибка, то выведем описание
                                    Network.getInstance().stop();  // закрываем
                                }
                                if (future.isSuccess()) {
                                    System.out.println("Файл успешно отправлен клиенту");
                                    Network.getInstance().stop();  // закрываем
                                }
                            });
                            //***********************************

                        } else if (comandParts[0].equalsIgnoreCase("/Close")) { // команда закрытия сервера
                            System.out.println("Server shutdown");
                            ctx.close();
                        }
                    } else if (((MyMessage) msg).getMessageType() == MessageType.TEXT) {
                        System.out.println("Client text message: " + ((MyMessage) msg).getText());
                        //ctx.writeAndFlush(new MyMessage("Hello Client!", MessageType.TEXT));
                    }
                } else {
                    System.out.printf("Server received wrong object!");
                }
            }

        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
