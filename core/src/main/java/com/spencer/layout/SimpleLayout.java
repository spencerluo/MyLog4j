package com.spencer.layout;

import com.spencer.api.Layout;
import com.spencer.api.LogEvent;
import com.spencer.plugin.Plugin;

@Plugin(name = "SimpleLayout", elementType = "com/spencer/layout")
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
