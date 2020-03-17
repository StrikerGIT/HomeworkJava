package com.striker.geekcloud.server;

import com.striker.geekcloud.common.FileSender;
import com.striker.geekcloud.common.File_State;
import com.striker.geekcloud.common.State;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    private String stringfilename;

    private State currentState = State.WAIT_COMMAND;
    private File_State currentFileState = File_State.NAME_LENGTH;
    private int nextLength;
    private long fileLength;
    private long receivedFileLength;
    private BufferedOutputStream out;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = ((ByteBuf) msg);

        while   (buf.readableBytes() > 0) {
        if (currentState == State.WAIT_COMMAND) {
            byte readed = buf.readByte();
            if (readed == (byte) 14) { //команда загрузки файла на сервер
                System.out.println("Получена команда загрузки файла на сервер");
                receivedFileLength = 0L;
                currentState = State.WAIT_FILE;
            }
            if (readed == (byte) 15) {//команда на загрузку файла с сервера
                currentState = State.SENT_FILE;
                System.out.println("Получена команда передать файл на клиент");
            }
        }

        if (currentState == State.WAIT_FILE) { // ловим инфо по файлу
            if (currentFileState == File_State.NAME_LENGTH) {
                if (buf.readableBytes() >= 4) {
                    System.out.println("Получили длину имени файла");
                    nextLength = buf.readInt();
                    currentFileState = File_State.NAME;
                }
            }
            if (currentFileState == File_State.NAME) {
                if (buf.readableBytes() >= nextLength) {
                    byte[] fileName = new byte[nextLength];
                    buf.readBytes(fileName);
                    System.out.println("Получено имя файла - " + new String(fileName, "UTF-8"));
                    out = new BufferedOutputStream(new FileOutputStream("server_storage/" + new String(fileName)));
                    currentFileState = File_State.FILE_LENGTH;
                }
            }
            if (currentFileState == File_State.FILE_LENGTH) {
                if (buf.readableBytes() >= 8) {
                    fileLength = buf.readLong();
                    System.out.println("Длина файла- " + fileLength);
                    currentState = State.WAIT_FILE;
                    currentFileState = File_State.FILE;
                }
            }
                if (currentFileState == File_State.FILE) { // ловим файл
                    while (buf.readableBytes() > 0) {
                        out.write(buf.readByte());
                        receivedFileLength++;
                        if (fileLength == receivedFileLength) {
                            currentState = State.WAIT_COMMAND;
                            currentFileState = File_State.NAME_LENGTH;
                            System.out.println("Файл успешно загружен");
                            out.close();
                            break;
                        }
                    }
                }

        }

        if (currentState == State.SENT_FILE) { // получаем имя файла
            if (currentFileState == File_State.NAME_LENGTH) {
                if (buf.readableBytes() >= 4) {
                    System.out.println("Получили имя файла");
                    nextLength = buf.readInt();
                    currentFileState = File_State.NAME;
                }
            }
            if (currentFileState == File_State.NAME) {
                if (buf.readableBytes() >= nextLength) {
                    byte[] fileName = new byte[nextLength];
                    buf.readBytes(fileName);
                    stringfilename = new String(fileName, "UTF-8");
                    System.out.println("Файл " + stringfilename + " отправлен клиенту");
                    currentFileState = File_State.FILE;
                }
            }

            if (currentFileState == File_State.FILE) {
//                // отправляем файл
                FileSender.sendFile(Paths.get("server_storage/" + stringfilename), ctx.channel(), future -> {
                    if (!future.isSuccess()) {
                        future.cause().printStackTrace();
                        System.out.println("Ошибка отправки файла клиенту");
                    }
                    if (future.isSuccess()) {
                        System.out.println("Файл успешно передан клиенту");
                    }
                });
//
                currentState = State.WAIT_COMMAND;
               currentFileState = File_State.NAME_LENGTH;
            }
        }
    }
        if (buf.readableBytes() == 0) {
            buf.release();
        }


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
