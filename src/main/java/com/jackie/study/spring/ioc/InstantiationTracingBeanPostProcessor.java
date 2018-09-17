package com.jackie.study.spring.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by jackiew on 6/28/2017.
 */
public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("Bean '" + beanName + "' created : " + bean.toString());
        return bean;
    }
}
