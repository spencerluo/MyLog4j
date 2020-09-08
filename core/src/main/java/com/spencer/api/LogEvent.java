package com.spencer.api;

public class LogEvent {

    private String loggerName;

    private Level level;

    private Message message;

    private String threadName;

    public LogEvent(String loggerName, Level level, Message message, String threadName) {
        this.loggerName = loggerName;
        this.level = level;
        this.message = message;
        this.threadName = threadName;
    }


    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
