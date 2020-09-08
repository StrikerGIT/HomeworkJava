package com.geekbrains.rabbitmq.blog;

public class Topic {

    private String tag;
    private String content;

    public Topic(String tag, String content) {
        this.tag = tag;
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public String getContent() {
        return content;
    }
}
