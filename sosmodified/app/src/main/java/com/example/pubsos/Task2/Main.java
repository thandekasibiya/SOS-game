package com.example.pubsos.Task2;
import java.util.HashMap;
import java.util.Map;

import com.example.pubsos.Task1.PubSubBroker;
import com.example.pubsos.Task1.Subscriber;

public class Main
{

    private Controller controller;
    public Main()
    {
        controller = new Controller();
        // Get the broker.
        PubSubBroker broker = PubSubBroker.getInstance();

        // Some subscribers.
        Subscriber s01 = (publisher, topic, params)
                -> System.out.printf("s01: %s message received.\n", topic);
        Subscriber s03 = (publisher, topic, params)
                -> checkMe();
        // Subscribe for some topics.
        System.out.println("Subscribing...");
        broker.subscribe("hello", s01);
        broker.subscribe("checkMe", s03);
        broker.subscribe("goodbye", s01);
        // Publishing
        System.out.println("Publishing...");
        Map<String, Object> params = new HashMap<>();
        params.put("name", "Bob");
        params.put("extra", 10);
        broker.publish(this, "hello", params);
        broker.publish(this, "goodbye", null);
        System.out.println("Unsubscribing s01...");
        broker.unsubscribe(s01);
        System.out.println("Republishing...");
        broker.publish(this, "hello", params);
        broker.publish(this, "goodbye", null);
    }
    public static void main(String[] args) {
        new Main();
    }
    public int checkMe()
    {
        return 0;
    }
}
