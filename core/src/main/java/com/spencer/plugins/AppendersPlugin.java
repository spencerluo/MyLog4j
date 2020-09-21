package com.spencer.plugins;

import com.spencer.api.Appender;

import java.util.HashMap;
import java.util.Map;

@Plugin(name = "appenders")
public class AppendersPlugin {


    @PluginFactory
    public Map<String, Appender> createAppenders(@PluginElement("array") Appender[] appenders){
        Map<String, Appender> appenderMap = new HashMap<>();
        for (Appender appender : appenders) {
            appenderMap.put(appender.getName(), appender);
        }

        return appenderMap;
    }
}
