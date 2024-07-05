package com.javarush.lesson05.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@EnableAspectJAutoProxy
@Aspect
public class BasePointcut {

    @Pointcut("within(com..*Service)")
    public void isService(){

    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void isServiceAnnotation(){

    }

    @Pointcut("execution(public * com..*Service.get(*))")
    public void getFromService(){

    }
}
