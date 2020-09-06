package layout;

import api.Layout;
import api.LogEvent;

public class SimpleLayout implements Layout {
    @Override
    public String formatEvent(LogEvent logEvent) {
        StringBuilder builder = new StringBuilder();
        builder.append("[").append(logEvent.getLevel().name()).append("] ");
        builder.append(logEvent.getLoggerName()).append(" ");
        builder.append(logEvent.getMessage().getFormattedMessage());
        builder.append("\n");

        return builder.toString();
    }
}
