package appender;

import api.AppenderBase;
import api.Layout;
import api.LogEvent;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamAppender extends AppenderBase {
    private OutputStream outputStream;
    private boolean immediateFlush;

    public OutputStreamAppender(String name, Layout layout, OutputStream outputStream, boolean immediateFlush) {
        super(name, layout);
        this.outputStream = outputStream;
        this.immediateFlush = immediateFlush;
    }



    @Override
    public void append(LogEvent logEvent) {
        synchronized (this){
            try {
                outputStream.write(getLayout().formatEvent(logEvent).getBytes());
                if (immediateFlush) {
                    outputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
