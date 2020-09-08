package com.spencer.configuration;

import com.spencer.api.Appender;
import com.spencer.api.BaseConfiguration;
import com.spencer.api.Layout;
import com.spencer.api.LoggerConfig;
import com.spencer.appender.ConsoleAppender;
import com.spencer.layout.SimpleLayout;

public class DefaultConfiguration extends BaseConfiguration {
    private String defaultName = "default";

    public DefaultConfiguration() {
        setName(defaultName);
        Layout layout = new SimpleLayout();
        Appender appender = ConsoleAppender.createAppender("console", layout, "SYSTEM_OUT");
        LoggerConfig rootConfig = getRoot();
        rootConfig.addAppender(appender);
    }


}
