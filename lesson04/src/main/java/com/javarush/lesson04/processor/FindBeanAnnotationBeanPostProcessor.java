package com.javarush.lesson04.processor;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.AccessibleObject;
import java.util.Arrays;

@AllArgsConstructor
public class FindBeanAnnotationBeanPostProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        Arrays.stream(aClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(FindBean.class))
                .peek(AccessibleObject::trySetAccessible)
                .forEach(f-> ReflectionUtils.setField(f,bean,applicationContext.getBean(f.getType())));
        Arrays.stream(aClass.getDeclaredMethods())
                .filter(m->m.getName().startsWith("set"))
                .filter(m->m.getParameterTypes().length==1)
                .filter(m->m.isAnnotationPresent(FindBean.class)
                           || m.getParameters()[0].isAnnotationPresent(FindBean.class))
                .forEach(m-> ReflectionUtils
                        .invokeMethod(m,bean,applicationContext.getBean(m.getParameterTypes()[0])));


        return bean;
    }
}
