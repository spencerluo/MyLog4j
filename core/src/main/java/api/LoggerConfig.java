package api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoggerConfig {
    private String name;
    private Level level;
    private List<AppenderRef> appenderRefs = new ArrayList<>();
    private Map<String, Appender> appenderMap = new ConcurrentHashMap<>();

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

}
