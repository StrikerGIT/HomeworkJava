package com.geekbrains.rabbitmq.blog;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Blog {
    private static final String javaRoutingKey = "Java";
    private static final String phpRoutingKey = "PHP";
    private static final String otherRoutingKey = "Other";
    private static final String EXCHANGE_NAME = "main_topic_exchange";


    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {

            configure(channel);

            Topic topic1 = new Topic("Java","1234567890123456789000");
            Topic topic2 = new Topic("Other","1234567890123456789000");
            Topic topic3 = new Topic("Other","1234567890123456789000");
            Topic topic4 = new Topic("PHP","1234567890123456789000");
            Topic topic5 = new Topic("Java","1234567890123456789000");

            send(channel, topic1);
            send(channel, topic2);
            send(channel, topic3);
            send(channel, topic4);
            send(channel, topic5);

            System.out.println(" [x] Publications are over for today. Come back tomorrow.[x]'");
        }
    }

    public static void send(Channel channel, Topic topic) throws IOException {
        channel.basicPublish(EXCHANGE_NAME, topic.getTag(), null, topic.getContent().getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + topic.getTag() + "'with content:'" + topic.getContent() + "'");
    }

    public static void configure(Channel channel) throws IOException {

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        channel.queueDeclare("javaQueue", false, false, false, null);
        channel.queueDeclare("phpQueue", false, false, false, null);
        channel.queueDeclare("otherQueue", false, false, false, null);
        channel.queueDeclare("allQueue", false, false, false, null);

        channel.queueBind("javaQueue", EXCHANGE_NAME, "Java");
        channel.queueBind("phpQueue", EXCHANGE_NAME, "PHP");
        channel.queueBind("otherQueue", EXCHANGE_NAME, "Other");
        channel.queueBind("allQueue", EXCHANGE_NAME, "*");
    }
}
