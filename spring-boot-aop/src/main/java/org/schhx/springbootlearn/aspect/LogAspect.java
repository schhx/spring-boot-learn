package org.schhx.springbootlearn.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 切面
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

    /**
     * 切点
     */
    @Pointcut("execution(public * org.schhx.springbootlearn.controller..*(..))")
    public void pointcut() {

    }

    /**
     * 前置通知
     *
     * @param joinPoint 可以获取目标对象的信息,如类名称,方法参数,方法名称等
     */
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        log.info("args: {}", joinPoint.getArgs());
    }

    /**
     * 后置通知
     *
     * @param joinPoint 可以获取目标对象的信息,如类名称,方法参数,方法名称等
     */
    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("我是后置通知");
    }

    /**
     * 后置通知
     *
     * @param joinPoint   可以获取目标对象的信息,如类名称,方法参数,方法名称等
     * @param returnValue 方法返回值
     */
    @AfterReturning(value = "pointcut()", returning = "returnValue")
    public void afterReturning2(JoinPoint joinPoint, Object returnValue) {
        log.info("returnValue: {}", returnValue);
    }

    /**
     * 异常通知
     *
     * @param joinPoint 可以获取目标对象的信息,如类名称,方法参数,方法名称等
     * @param e         方法抛出的异常
     */
    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("异常通知", e);
    }

    /**
     * 最终通知
     *
     * @param joinPoint 可以获取目标对象的信息,如类名称,方法参数,方法名称等
     */
    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        log.info("最终通知");
    }


    /**
     * 环绕通知
     *
     * @param pjp 除了 JoinPoint 的全部功能以外，还可以执行被通知的方法
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        try {
            log.info("前置通知");
            Object response = pjp.proceed();
            log.info("后置通知");
            return response;
        } catch (Throwable e) {
            log.error("异常通知", e);
            throw e;
        }
    }
}
