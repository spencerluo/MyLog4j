package context;

import api.Configuration;
import api.Logger;
import api.LoggerConfig;
import api.LoggerContext;
import configuration.DefaultConfiguration;
import logger.Log4jLogger;

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
