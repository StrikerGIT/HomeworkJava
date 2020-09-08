package com.geekbrains.rabbitmq.subscriber;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Main {
    private static final String EXCHANGE_NAME = "main_topic_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {

        Subscriber Subcriber1 = new Subscriber("Max");
        Subscriber Subcriber2 = new Subscriber("Alex");
        Subscriber Subcriber3 = new Subscriber("Joe");

        Subcriber1.subscribe("javaQueue","Java");
        Subcriber2.subscribe("javaQueue","Java");
        Subcriber2.subscribe("otherQueue","Other");
        Subcriber3.subscribe("phpQueue","PHP");
        Subcriber3.subscribe("otherQueue","Other");
        Subcriber3.unsubscribe("otherQueue");
        Subcriber3.subscribe("allQueue","*");

        waitMessage(Subcriber1);
        waitMessage(Subcriber2);
        waitMessage(Subcriber3);
    }

    public static void configure(Subscriber subscriber ,Connection connection) throws IOException {

        //подключаем все очереди, на которые есть подписка
        subscriber.getSubscribes().forEach((k,v) -> {
            try {
                Channel channel = connection.createChannel();
                channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
                channel.queueBind(k, EXCHANGE_NAME, v);

                System.out.println(" [*] User: " + subscriber.getName() + " [*] Waiting for messages with routing key (" + v + "):");

                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), "UTF-8");
                    System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");

                };
                channel.basicConsume(k, true, deliverCallback, consumerTag -> {
                });            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void waitMessage(Subscriber subscriber) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        configure(subscriber, connection);


    }

}

