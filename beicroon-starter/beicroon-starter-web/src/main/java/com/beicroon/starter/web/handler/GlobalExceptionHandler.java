package com.beicroon.starter.web.handler;

import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.construct.exception.BusinessException;
import com.beicroon.construct.pay.exception.WechatNotifyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.profiles.active:local}")
    private String activeProfile;

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    public WebResult<String> handleException(BusinessException ex) {
        return WebResult.error(ex.getCode(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public WebResult<String> handleException(HttpRequestMethodNotSupportedException ex) {
        return WebResult.error(405, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResult<String> handleException(MethodArgumentNotValidException ex) {
        return WebResult.error(412, ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public WebResult<String> handleException(MissingServletRequestParameterException ex) {
        return WebResult.error(412, String.format("[%s]参数缺失", ex.getParameterName()));
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(IllegalStateException.class)
    public WebResult<String> handleException(IllegalStateException ex) {
        return WebResult.error(412, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(IllegalArgumentException.class)
    public WebResult<String> handleException(IllegalArgumentException ex) {
        return WebResult.error(412, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public WebResult<String> handleException(HttpMessageNotReadableException ex) {
        return WebResult.error(412, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NoResourceFoundException.class)
    public WebResult<String> handleException(NoResourceFoundException ex) {
        log.warn("接口不存在=>{}", ex.getMessage());

        return WebResult.error(500, "接口不存在");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WechatNotifyException.class)
    public Map<String, String> handleException(WechatNotifyException ex) {
        log.error("微信支付回调异常=>{}", ex.getMessage());

        return Map.of("code", ex.getCode(), "message", ex.getError());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public WebResult<String> handleException(Exception ex) {
        String message = String.format("%s:%s", ex.getClass().getName(), ex.getMessage());

        log.error("系统异常=>{}", message, ex);

        if ("pro".contains(this.activeProfile)) {
            return WebResult.error(500, "系统异常!请稍后再试~");
        }

        return WebResult.error(500, message);
    }

}
