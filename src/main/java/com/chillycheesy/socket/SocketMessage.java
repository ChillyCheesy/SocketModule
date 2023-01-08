package com.chillycheesy.socket;

public class SocketMessage<T> {

    private String key;
    private T message;

    public SocketMessage(String key, T message) {
        this.key = key;
        this.message = message;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public T getMessage() {
        return message;
    }

}
