package com.qianzhiyang.exception;

/**
 * @author qianzhiyang <qianzhiyang@kuaishou.com>
 * Created on 2021-10-10
 */
public class PyramidException extends RuntimeException {
    protected PyramidException(String message) {
        super(message);
    }

    protected PyramidException() {
    }

    public static PyramidException of(String message) {
        return new PyramidException(message);
    }
}
