package configuration;

import api.Appender;
import api.BaseConfiguration;
import api.Layout;
import api.LoggerConfig;
import appender.ConsoleAppender;
import layout.SimpleLayout;

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
