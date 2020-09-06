package api;

public interface Configuration {

    LoggerConfig getLoggerConfig(String name);

    void addAppender(Appender appender);

}
