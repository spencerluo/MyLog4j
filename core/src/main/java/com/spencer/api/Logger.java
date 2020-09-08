package com.spencer.api;

public interface Logger {
    void info(String message);

    void info(Object object);

    void debug(String message);

    void debug(Object object);

    void error(String message);

    void error(Object object);

}
