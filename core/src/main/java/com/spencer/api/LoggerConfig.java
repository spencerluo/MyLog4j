package com.spencer.api;

import com.spencer.plugins.Plugin;
import com.spencer.plugins.PluginAttr;
import com.spencer.plugins.PluginElement;
import com.spencer.plugins.PluginFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Plugin(name = "logger")
public class LoggerConfig {
    private String name;
    private Level level;
    private List<AppenderRef> appenderRefs = new ArrayList<>();
    private Map<String, Appender> appenderMap = new ConcurrentHashMap<>();

    public LoggerConfig(){

    }

    public LoggerConfig(String name){
        this.name = name;
        this.level = Level.ERROR;
    }

    public LoggerConfig(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public LoggerConfig(String name, Level level, List<AppenderRef> appenderRefs) {
        this.name = name;
        this.level = level;
        this.appenderRefs = appenderRefs;
    }


    public List<AppenderRef> getAppenderRefs() {
        return appenderRefs;
    }

    public void addAppender(Appender appender){
        appenderMap.put(appender.getName(), appender);
    }

    public Appender getAppender(String name){
        return appenderMap.get(name);
    }

    public void log(String name, Level level, Message message){
        LogEvent event = new LogEvent(name, level, message, null);
        callAppenders(event);
    }

    private void callAppenders(LogEvent logEvent){
        for (Appender appender: appenderMap.values()){
            appender.append(logEvent);
        }
    }

    @PluginFactory
    public LoggerConfig createLogger(@PluginElement("appender-ref") AppenderRef[] refs,
                                     @PluginAttr("level") String loggerLevel,
                                     @PluginAttr("name") String name){
        List<AppenderRef> appenderRefs = Arrays.asList(refs);
        Level level;
        try {
            level = loggerLevel == null? Level.ERROR: Level.valueOf(loggerLevel);
        }catch (IllegalArgumentException e){
            level = Level.ERROR;
        }
        return new LoggerConfig(name, level, appenderRefs);
    }

    @Plugin(name = "Root")
    public static class RootLogger extends LoggerConfig{

        @PluginFactory
        public LoggerConfig createLogger(@PluginElement("appender-ref") AppenderRef[] refs,
                                         @PluginAttr("level") String loggerLevel){
            List<AppenderRef> appenderRefs = Arrays.asList(refs);
            Level level;
            try {
                level = loggerLevel == null? Level.ERROR: Level.valueOf(loggerLevel);
            }catch (IllegalArgumentException e){
                level = Level.ERROR;
            }
            return new LoggerConfig("root", level, appenderRefs);
        }
    }

}
