package com.qianzhiyang.exception;

/**
 * @author qianzhiyang <qianzhiyang@kuaishou.com>
 * Created on 2021-10-10
 */
public class PyramidBuildRelationException extends PyramidException {
    protected PyramidBuildRelationException(String message) {
        super(message);
    }

    protected PyramidBuildRelationException() {
    }

    public static PyramidBuildRelationException of(String message) {
        return new PyramidBuildRelationException(message);
    }
}
