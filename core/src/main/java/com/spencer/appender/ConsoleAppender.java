package com.spencer.appender;

import com.spencer.api.Layout;
import com.spencer.plugin.Plugin;

import java.io.OutputStream;

@Plugin(name = "console", elementType = "com/spencer/appender")
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

    public static ConsoleAppender createAppender(String name, Layout layout, String t){
        Target target = t == null ? Target.SYSTEM_OUT : Target.valueOf(t);
        return new ConsoleAppender(name, layout, getOutputStream(target));
    }

    private static OutputStream getOutputStream(Target target){
        return target == Target.SYSTEM_OUT? System.out: System.err;
    }
}
