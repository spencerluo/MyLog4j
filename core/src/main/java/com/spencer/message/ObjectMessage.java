package com.spencer.message;

import com.spencer.api.Message;

public class ObjectMessage implements Message {

    private final Object object;

    public ObjectMessage(Object object) {
        this.object = object;
    }
    
    @Override
    public String getFormattedMessage() {
        return object.toString();
    }
}
