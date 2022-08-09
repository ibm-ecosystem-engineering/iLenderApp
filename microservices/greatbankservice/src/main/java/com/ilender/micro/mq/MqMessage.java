package com.ilender.micro.mq;

public class MqMessage {

    int id;

    public MqMessage() {
    }

    public MqMessage(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
