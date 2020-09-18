package com.spencer.appender;

import com.spencer.api.Appender;
import com.spencer.api.Layout;
import com.spencer.plugins.Plugin;
import com.spencer.plugins.PluginAttr;
import com.spencer.plugins.PluginElement;
import com.spencer.plugins.PluginFactory;

import java.io.OutputStream;

@Plugin(name = "console", elementType = "appender")
public class ConsoleAppender extends OutputStreamAppender {

    public enum Target {
        /** Standard output. */
        SYSTEM_OUT,
        /** Standard error output. */
        SYSTEM_ERR
    }

    private ConsoleAppender(String name, Layout layout, OutputStream outputStream) {
        super(name, layout, outputStream, true);
    }

    @PluginFactory
    public static Appender createAppender(@PluginAttr("name") String name,
                                          @PluginElement("layout") Layout layout,
                                          @PluginAttr("target") String t){
        Target target = t == null ? Target.SYSTEM_OUT : Target.valueOf(t);
        return new ConsoleAppender(name, layout, getOutputStream(target));
    }

    private static OutputStream getOutputStream(Target target){
        return target == Target.SYSTEM_OUT? System.out: System.err;
    }
}
