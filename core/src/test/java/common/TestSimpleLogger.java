package common;

import logger.SimpleLogger;

public class TestSimpleLogger {

    public static void main(String[] args) {
        SimpleLogger simpleLogger = new SimpleLogger("test");
        simpleLogger.info("info");
        simpleLogger.debug("debug");
        simpleLogger.info(new People("spencer", 4));

        simpleLogger.getEntries().forEach(System.out::println);
    }
}
