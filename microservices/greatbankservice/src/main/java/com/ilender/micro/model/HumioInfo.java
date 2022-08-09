package com.ilender.micro.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HumioInfo {

    private Map fields = null;

    private List messages = new ArrayList();

    public List getMessages() {
        return messages;
    }

    public void setMessages(List messages) {
        this.messages = messages;
    }

    public Map getFields() {
        return fields;
    }

    public void setFields(Map fields) {
        this.fields = fields;
    }
}

