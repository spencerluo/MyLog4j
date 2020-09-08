package com.spencer.context;

import com.spencer.api.Logger;
import com.spencer.api.LoggerContext;
import com.spencer.logger.SimpleLogger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SimpleContext implements LoggerContext {
    private ConcurrentMap<String, Logger> loggerMap = new ConcurrentHashMap<>();

    @Override
    public Logger getLogger(String name) {
        Logger logger = loggerMap.get(name);
        if (logger == null){
            Logger simpleLogger = new SimpleLogger(name);
            Logger newLogger = loggerMap.putIfAbsent(name, simpleLogger);
            return newLogger == null ? simpleLogger: newLogger;
        }

        return logger;
    }
}
