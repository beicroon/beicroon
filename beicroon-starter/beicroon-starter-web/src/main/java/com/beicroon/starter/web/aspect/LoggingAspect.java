package com.beicroon.starter.web.aspect;

import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.json.utils.JsonUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Resource
    private HttpServletRequest request;

    @Around("within(com.beicroon..*.web.*.controller..*)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(
                "Request Log: path={}, params={}, auth={}",
                request.getRequestURI(),
                JsonUtils.toJson(joinPoint.getArgs()),
                JsonUtils.toJson(AuthUtils.getAuthThreadInfo())
        );

        return joinPoint.proceed();
    }

}
