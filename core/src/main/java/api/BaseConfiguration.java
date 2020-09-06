package api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BaseConfiguration implements Configuration {
    private Map<String, LoggerConfig> loggerConfigMap = new ConcurrentHashMap<>();
    private Map<String, Appender> appenderMap = new ConcurrentHashMap<>();
    private LoggerConfig root = new LoggerConfig("root");
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public LoggerConfig getLoggerConfig(String name) {
        LoggerConfig config = loggerConfigMap.get(name);
        if (config != null) {
            return config;
        }

        return root;
    }

    @Override
    public void addAppender(Appender appender) {
        appenderMap.put(appender.getName(), appender);
    }

    public LoggerConfig getRoot(){
        return root;
    }

}
