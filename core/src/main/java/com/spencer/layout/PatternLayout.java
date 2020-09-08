package com.spencer.layout;

import com.spencer.api.Layout;
import com.spencer.api.LogEvent;

public class PatternLayout implements Layout {
    private String pattern;

    public PatternLayout(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String formatEvent(LogEvent logEvent) {
        return null;
    }
}
