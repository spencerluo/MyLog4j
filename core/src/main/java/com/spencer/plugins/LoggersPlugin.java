package com.spencer.plugins;

import com.spencer.api.LoggerConfig;

import java.util.HashMap;
import java.util.Map;

@Plugin(name = "loggers")
public class LoggersPlugin {

    @PluginFactory
    public Map<String, LoggerConfig> createLoggers(@PluginElement("loggers") LoggerConfig[] loggerConfigs){
        Map<String, LoggerConfig> loggerConfigMap = new HashMap<>();
        for (LoggerConfig loggerConfig : loggerConfigs) {
            loggerConfigMap.put(loggerConfig.getName(), loggerConfig);
        }

        return loggerConfigMap;
    }
}
