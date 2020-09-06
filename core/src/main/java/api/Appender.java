package api;

public interface Appender {

    void append(LogEvent logEvent);

    Layout getLayout();

    String getName();
}
