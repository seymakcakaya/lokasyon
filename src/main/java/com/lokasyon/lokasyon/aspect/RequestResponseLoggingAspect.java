package com.lokasyon.lokasyon.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@ConditionalOnProperty(name = "app.logging.enabled", havingValue = "true")
public class RequestResponseLoggingAspect  {
   private static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggingAspect.class);

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void requestMappingMethods() {}

    @Around("requestMappingMethods()")
    public Object logRequestAndResponse(ProceedingJoinPoint joinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        logger.info("Request: {} {} - Parameters: {}", request.getMethod(), request.getRequestURI(), request.getParameterMap());
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (result instanceof ResponseEntity) {
            ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
            logger.info("Method {} executed in {} ms - Response: Status {}", joinPoint.getSignature(),elapsedTime,responseEntity.getStatusCode());
        }
        return result;
    }
}
