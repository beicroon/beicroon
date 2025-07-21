package com.beicroon.construct.pay.exception;

import lombok.Getter;

@Getter
public class WechatNotifyException extends RuntimeException {

    private final String code = "FAIL";

    private final String error;

    public WechatNotifyException(String message) {
        super(message);

        this.error = message;
    }

    public WechatNotifyException(Throwable cause) {
        super(cause);

        this.error = cause.getMessage();
    }

    public WechatNotifyException(String message, Throwable cause) {
        super(message, cause);

        this.error = message;
    }

}
