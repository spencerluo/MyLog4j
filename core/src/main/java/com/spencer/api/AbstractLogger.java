package com.spencer.api;

public abstract class AbstractLogger implements Logger{
    @Override
    public void info(String message) {
        if (isEnabled(Level.INFO, message)) {
            log(Level.INFO, message);
        }
    }

    @Override
    public void info(Object object) {
        if (isEnabled(Level.INFO, object)) {
            log(Level.INFO, object);
        }
    }

    @Override
    public void debug(String message) {
        if (isEnabled(Level.DEBUG, message)) {
            log(Level.DEBUG, message);
        }
    }

    @Override
    public void debug(Object object) {
        if (isEnabled(Level.DEBUG, object)) {
            log(Level.DEBUG, object);
        }
    }

    @Override
    public void error(String message) {
        if (isEnabled(Level.ERROR, message)) {
            log(Level.ERROR, message);
        }
    }

    @Override
    public void error(Object object) {
        if (isEnabled(Level.ERROR, object)) {
            log(Level.ERROR, object);
        }
    }


    protected abstract boolean isEnabled(Level level, Message message);

    protected abstract boolean isEnabled(Level level, String message);

    protected abstract boolean isEnabled(Level level, Object object);


    protected abstract void log(Level level, Message message);

    protected abstract void log(Level level, String message);

    protected abstract void log(Level level, Object object);


}
