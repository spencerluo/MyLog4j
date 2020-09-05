package message;

import api.Message;

public class SimpleMessage implements Message {

    private String message;

    public SimpleMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String getFormattedMessage() {
        return message;
    }
}
