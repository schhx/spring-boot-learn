package org.schhx.springbootlearn.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Slf4j
@Component
public class LogAspect {

    @Pointcut("execution(public * org.schhx.springbootlearn.controller.*.*(..))")
    public void log() {

    }

    @Around("log()")
    public Object logAroundAllMethods(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        String handler = pjp.getSignature().toString();
        Object[] args = pjp.getArgs();
        try {
            log.info("begin invoke handler: {}, args: {}", handler, args);
            Object response = pjp.proceed();
            log.info("end invoke handler: {}, args: {}, response: {}, cost: {}ms", handler, args, "ok", System.currentTimeMillis() - begin);
            return response;
        } catch (Throwable e) {
            log.info("end invoke handler: {}, args: {}, response with error :{} ,cost:{}", handler, args, e.getMessage(), System.currentTimeMillis() - begin);
            throw e;
        }
    }
}
