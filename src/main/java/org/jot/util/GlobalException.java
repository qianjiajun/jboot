package org.jot.util;

import org.jot.enumeration.StatusCode;

public class GlobalException extends Exception {

    private int code = 500;
    private String localMessage;

    public GlobalException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.code = statusCode.getCode();
        this.localMessage = statusCode.getMessage();
    }

    public GlobalException(String message) {
        super(message);
        this.localMessage = message;
    }

    public GlobalException(int code, String message) {
        super(message);
        this.localMessage = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getLocalMessage() {
        return localMessage;
    }
}
