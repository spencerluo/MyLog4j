package common;

import api.Logger;
import api.LoggerContext;
import context.Log4jContext;
import context.SimpleContext;
import logger.SimpleLogger;

public class TestSimpleLogger {

    public static void main(String[] args) {
        LoggerContext context = new Log4jContext();
        Logger logger = context.getLogger("spencer");
        logger.info("abc");
        logger.info("efg");
        Logger logger2 = context.getLogger("david");
        logger2.info("hig");
        logger2.info("kef");
    }
}
