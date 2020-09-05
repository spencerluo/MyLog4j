package common;

import api.Logger;
import api.LoggerContext;
import context.SimpleContext;
import logger.SimpleLogger;

public class TestSimpleLogger {

    public static void main(String[] args) {
        LoggerContext context = new SimpleContext();
        Logger simpleLogger = context.getLogger("spencer");
        simpleLogger.info("info");
        simpleLogger.debug("debug");
        simpleLogger.info(new People("spencer", 4));

    }
}
