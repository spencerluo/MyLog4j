package com.spencer.logger;

import com.spencer.api.Level;
import com.spencer.api.Logger;
import com.spencer.api.LoggerConfig;
import com.spencer.message.SimpleMessage;

public class Log4jLogger implements Logger {
    private String name;
    private LoggerConfig config;

    public Log4jLogger(String name, LoggerConfig config){
        this.name = name;
        this.config = config;
    }

    @Override
    public void info(String message) {
        this.config.log(name, Level.INFO, new SimpleMessage(message));
    }

    @Override
    public void info(Object object) {

    }

    @Override
    public void debug(String message) {

    }

    @Override
    public void debug(Object object) {

    }

    @Override
    public void error(String message) {

    }

    @Override
    public void error(Object object) {

    }
}
