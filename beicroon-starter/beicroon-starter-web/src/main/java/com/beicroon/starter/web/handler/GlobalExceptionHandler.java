package com.beicroon.starter.web.handler;

import com.beicroon.construct.entity.Result;
import com.beicroon.construct.entity.WebResult;
import com.beicroon.construct.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    public Result<String> handleException(BusinessException ex) {
        return WebResult.error(ex.getCode(), ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<String> handleException(HttpRequestMethodNotSupportedException ex) {
        return WebResult.error(405, ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleException(MethodArgumentNotValidException ex) {
        return WebResult.error(412, ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<String> handleException(MissingServletRequestParameterException ex) {
        return WebResult.error(412, String.format("【%s】参数缺失", ex.getParameterName()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(IllegalStateException.class)
    public Result<String> handleException(IllegalStateException ex) {
        return WebResult.error(412, ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<String> handleException(IllegalArgumentException ex) {
        return WebResult.error(412, ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<String> handleException(HttpMessageNotReadableException ex) {
        return WebResult.error(412, ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception ex) {
        String message = String.format("%s:%s", ex.getClass().getName(), ex.getMessage());

        log.error(String.format("系统异常=>%s", message), ex);

        if ("pro".contains(this.activeProfile)) {
            return WebResult.error(500, "系统异常!请稍后再试~");
        }

        return WebResult.error(500, message);
    }

}
