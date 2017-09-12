package com.bjtu.ourbt.util;

public class NullImageException extends Exception {

    private static final long serialVersionUID = 1L;

    // 自定义异常
    public NullImageException() {
        super();
    }

    public NullImageException(final String msg) {
        super(msg);
    }

}
