package logger;

import api.AbstractLogger;
import api.Level;
import api.Message;
import message.ObjectMessage;
import message.SimpleMessage;

import java.util.ArrayList;
import java.util.List;

public class SimpleLogger extends AbstractLogger {

    private String name;
    private final List<String> array = new ArrayList<>();

    public SimpleLogger(String name) {
        this.name = name;
    }

    @Override
    protected boolean isEnabled(Level level, Message message) {
        return true;
    }

    @Override
    protected boolean isEnabled(Level level, String message) {
        return true;
    }

    @Override
    protected boolean isEnabled(Level level, Object object) {
        return true;
    }

    @Override
    protected void log(Level level, Message message) {
        StringBuilder builder = new StringBuilder();
        builder.append("[").append(level.name()).append("] ");
        builder.append(this.name).append(" ");
        builder.append(message.getFormattedMessage());

        array.add(builder.toString());
    }

    @Override
    protected void log(Level level, String message) {
        log(level, new SimpleMessage(message));
    }

    @Override
    protected void log(Level level, Object object) {
        log(level, new ObjectMessage(object));
    }

    public List<String> getEntries(){
        return array;
    }
}
