package com.cwpad.rail.services;

import java.io.PrintWriter;
import java.io.StringWriter;

class DefaultJULWrapper implements Logger {
    private final java.util.logging.Logger logger;

    DefaultJULWrapper(Class<?> type) {
        logger = java.util.logging.Logger.getLogger(type.getName());
    }

    @Override
    public void debug(String msg) {
        logger.fine(msg);
    }

    @Override
    public void info(String msg) {
        logger.info(msg);
    }

    @Override
    public void warn(String msg) {
        logger.warning(msg);
    }

    @Override
    public void error(String msg, Throwable t) {
        StringWriter st = new StringWriter();
        PrintWriter w = new PrintWriter(st);
        t.printStackTrace(w);
        logger.severe(String.format("%s%n%s", msg, st));
    }
}
