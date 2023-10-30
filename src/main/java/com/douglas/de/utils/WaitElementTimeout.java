package com.douglas.de.utils;

public enum WaitElementTimeout {

    MINIMUM(2000),
    SMALL(4000),
    MEDIUM(10000),
    LARGE(20000),
    MAXIMUM(30000);

    private int timeout;
    private double timeoutScaleFactor = 1.0;

    WaitElementTimeout(int timeout) {
        this.timeout = new Float(timeout * timeoutScaleFactor).intValue();
    }

    public int getValue() {
        return timeout;
    }

    @Override
    public String toString() {
        return String.valueOf(timeout);
    }
}