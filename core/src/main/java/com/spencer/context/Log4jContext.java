package com.spencer.context;

import com.spencer.api.Configuration;
import com.spencer.api.Logger;
import com.spencer.api.LoggerConfig;
import com.spencer.api.LoggerContext;
import com.spencer.logger.Log4jLogger;
import com.spencer.configuration.DefaultConfiguration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Log4jContext implements LoggerContext {
    private Configuration configuration = new DefaultConfiguration();
    private Map<String, Logger> loggerMap = new ConcurrentHashMap<>();

    @Override
    public Logger getLogger(String name) {
        Logger logger = loggerMap.get(name);

        if (logger != null) {
            return logger;
        }

        LoggerConfig config = configuration.getLoggerConfig(name);
        logger = new Log4jLogger(name, config);
        Logger preLogger = loggerMap.putIfAbsent(name, logger);
        return preLogger == null? logger: preLogger;
    }
}
