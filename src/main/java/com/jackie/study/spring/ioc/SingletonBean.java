package com.jackie.study.spring.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by jackiew on 6/30/2017.
 */
public class SingletonBean implements ApplicationContextAware{
    private PrototypeBean prototypeBean;
    private ApplicationContext applicationContext;
    private PrototypeBeanLocator prototypeBeanLocator;
    private ObjectFactory<PrototypeBean> prototypeFactoryBean;

    public ObjectFactory<PrototypeBean> getPrototypeFactoryBean() {
        return prototypeFactoryBean;
    }

    public void setPrototypeFactoryBean(ObjectFactory<PrototypeBean> prototypeFactoryBean) {
        this.prototypeFactoryBean = prototypeFactoryBean;
    }

    public PrototypeBeanLocator getPrototypeBeanLocator() {
        return prototypeBeanLocator;
    }

    public void setPrototypeBeanLocator(PrototypeBeanLocator prototypeBeanLocator) {
        this.prototypeBeanLocator = prototypeBeanLocator;
    }

    public PrototypeBean getPrototypeBean() {
        /*if (applicationContext != null) {
            return (PrototypeBean) applicationContext.getBean("prototypeBean");
        }*/
       /* if (prototypeBeanLocator != null) {
            return prototypeBeanLocator.getPrototypeBean();
        }*/
       if(prototypeFactoryBean!=null){
           return prototypeFactoryBean.getObject();
       }
        return prototypeBean;
    }

    public  PrototypeBean injectPrototypeBean(){
        //this method will be override by spring's method inject
        return null;
    }


    public void setPrototypeBean(PrototypeBean prototypeBean) {
        this.prototypeBean = prototypeBean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
