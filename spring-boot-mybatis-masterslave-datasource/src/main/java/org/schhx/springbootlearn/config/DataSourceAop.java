package org.schhx.springbootlearn.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class DataSourceAop {

    @Pointcut("@annotation(SlaveDataSource)")
    public void aspect() {
    }

    @Before("aspect()")
    public void before(JoinPoint point) {
        Object target = point.getTarget();
        String methodName = point.getSignature().getName();
        Class<?> clazz = target.getClass();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        try {
            Method method = clazz.getMethod(methodName, parameterTypes);
            if (method != null && method.isAnnotationPresent(SlaveDataSource.class)) {
                DBContextHolder.setDbType(DataSourceConstant.SLAVE);
            }
        } catch (Exception e) {
            log.error("数据源切换切面异常", e);
        }
    }

    @After("aspect()")
    public void after() {
        DBContextHolder.clearDbType();
    }

    @AfterThrowing("aspect()")
    public void afterThrow() {
        DBContextHolder.clearDbType();
    }

}
