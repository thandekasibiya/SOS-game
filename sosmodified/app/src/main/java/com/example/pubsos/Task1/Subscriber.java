package com.example.pubsos.Task1;

import java.util.Map;

@FunctionalInterface
public interface Subscriber {
    void onPublished(Object publisher, String topic, Map<String, Object> params);
}

