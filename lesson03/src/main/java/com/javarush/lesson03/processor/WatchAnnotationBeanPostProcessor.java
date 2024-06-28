package com.javarush.lesson03.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WatchAnnotationBeanPostProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public WatchAnnotationBeanPostProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    private final Class<? extends Annotation> ANNOTATION_CLASS = Watch.class;

    private final Map<String, Class<?>> realClasses = new ConcurrentHashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (isAnnotationPresent(beanClass)) {
            realClasses.putIfAbsent(beanName, beanClass);
            System.out.println(beanName + " saved");
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (realClasses.containsKey(beanName)) {
            bean = proxy(bean, realClasses.get(beanName));
            System.out.println(beanName + " in proxy");
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }


    private boolean isAnnotationPresent(Class<?> aClass) {
        return aClass.isAnnotationPresent(ANNOTATION_CLASS) || Arrays.stream(aClass.getMethods())
                .anyMatch(m -> m.isAnnotationPresent(ANNOTATION_CLASS));
    }


    private Object proxy(Object beanOrProxy, Class<?> beanRealClass) {
        MethodInterceptor handler = (obj, method, args, proxy) -> {
            Object result;
            if (beanRealClass.isAnnotationPresent(ANNOTATION_CLASS)
                || method.isAnnotationPresent(ANNOTATION_CLASS)
            ) {
                System.out.printf("==  %s %s started ==%n", ANNOTATION_CLASS.getSimpleName(), method.getName());
                long start = System.nanoTime();
                result = proxy.invoke(beanOrProxy, args);
                long duration = System.nanoTime() - start;
                System.out.printf("==  %s %s complete atfer %.3f milliseconds==%n",
                        ANNOTATION_CLASS.getSimpleName(), method.getName(), duration/1000000.0);
                return result;
            } else {
                return proxy.invoke(beanOrProxy, args);
            }
        };
        return enhancerCreate(beanRealClass, handler);
        //return Enhancer.create(beanRealClass, handler); //if empty constructor
    }

    private Object enhancerCreate(Class<?> beanRealClass, MethodInterceptor handler) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanRealClass);
        enhancer.setCallback(handler);
        Constructor<?> constructor = Arrays
                .stream(beanRealClass.getConstructors())
                .max(Comparator.comparingInt(Constructor::getParameterCount))
                .orElseThrow();
        Object[] paramBeans = Arrays
                .stream(constructor.getParameterTypes())
                .map(applicationContext::getBean)
                .toArray();
        return enhancer.create(constructor.getParameterTypes(), paramBeans);
    }


}