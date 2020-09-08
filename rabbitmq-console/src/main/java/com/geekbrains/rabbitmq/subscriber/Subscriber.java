package com.geekbrains.rabbitmq.subscriber;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Subscriber {
    private String name;
    private Map<String,String> subscribes = new HashMap();

    public Subscriber(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getSubscribes() {
        return subscribes;
    }

    public void subscribe(String nameQueue,String tag){
        this.subscribes.put(nameQueue,tag);
    }

    public void unsubscribe(String nameQueue){
        this.subscribes.remove(nameQueue);
    }
}
