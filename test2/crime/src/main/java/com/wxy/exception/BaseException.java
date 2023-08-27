package com.wxy.exception;

/**
 * 自定义异常
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    public BaseException(String message) {
        this.message = message;
    }

    public BaseException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public BaseException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
