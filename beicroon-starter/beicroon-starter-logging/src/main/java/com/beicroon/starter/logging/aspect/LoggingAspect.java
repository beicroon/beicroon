package com.beicroon.starter.logging.aspect;

import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.json.utils.JsonUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Aspect
@Configuration
public class LoggingAspect {

    @Resource
    private HttpServletRequest request;

    private void logRequest(ProceedingJoinPoint joinPoint) {
        log.info(
                "Request Log: mapping={}, params={}, auth={}",
                String.format("%s=>%s", request.getMethod(), request.getRequestURI()),
                JsonUtils.toJson(joinPoint.getArgs()),
                JsonUtils.toJson(AuthUtils.getAuthThreadInfo())
        );
    }

    @Around("@annotation(ignore)")
    public Object around(ProceedingJoinPoint joinPoint, RequestMapping ignore) throws Throwable {
        this.logRequest(joinPoint);

        return joinPoint.proceed();
    }

    @Around("@annotation(ignore)")
    public Object around(ProceedingJoinPoint joinPoint, GetMapping ignore) throws Throwable {
        this.logRequest(joinPoint);

        return joinPoint.proceed();
    }

    @Around("@annotation(ignore)")
    public Object around(ProceedingJoinPoint joinPoint, PostMapping ignore) throws Throwable {
        this.logRequest(joinPoint);

        return joinPoint.proceed();
    }

    @Around("@annotation(ignore)")
    public Object around(ProceedingJoinPoint joinPoint, PutMapping ignore) throws Throwable {
        this.logRequest(joinPoint);

        return joinPoint.proceed();
    }

    @Around("@annotation(ignore)")
    public Object around(ProceedingJoinPoint joinPoint, DeleteMapping ignore) throws Throwable {
        this.logRequest(joinPoint);

        return joinPoint.proceed();
    }

}
