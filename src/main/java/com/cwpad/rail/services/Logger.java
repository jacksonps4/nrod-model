package com.cwpad.rail.services;

public interface Logger {
    void debug(String msg);

    void info(String msg);
    void warn(String msg);
    void error(String msg, Throwable t);

    static Logger getInstance(Class<?> type) {
        return new DefaultJULWrapper(type);
    }
}
