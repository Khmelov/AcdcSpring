package com.javarush.lesson05.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
@Slf4j
public class LogAopService {

    @Before("BasePointcut.isService() " +
            "|| BasePointcut.isServiceAnnotation()")
    public void runMethodFromService(JoinPoint joinPoint){
        log.warn("signature={} ",joinPoint.getSignature());
    }

    @Around(value = "BasePointcut.isService() && args(ids) && target(clazz)",
            argNames = "joinPoint,ids,clazz")
    public Object methodInService(ProceedingJoinPoint joinPoint, Object ids, Object clazz) throws Throwable{
        log.info("START signature={} ",joinPoint.getSignature());
        try {
            Object result = joinPoint.proceed();
            log.info("OK RESULT signature={} ",joinPoint.getSignature());
            return result;
        } catch (Throwable e) {
            log.warn("THROW {} signature={} ",e,joinPoint.getSignature());
            throw e;
        } finally {
            log.info("AFTER invoke signature={} ",joinPoint.getSignature());
        }
    }


}
