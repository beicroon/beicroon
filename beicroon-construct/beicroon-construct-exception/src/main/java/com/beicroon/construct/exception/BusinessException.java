package com.beicroon.construct.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final Integer code;

    private final String message;

    public BusinessException(String message) {
        this.code = 422;

        this.message = message;
    }

    public BusinessException(Integer code, String message) {
        this.code = code;

        this.message = message;
    }

}
