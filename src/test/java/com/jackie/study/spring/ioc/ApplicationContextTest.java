package com.jackie.study.spring.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by jackiew on 6/26/2017.
 */
public class ApplicationContextTest {
    @Test
    public void applicationInitializationTest() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"daos.xml","services.xml"});
        PetStoreService petStoreService = context.getBean(PetStoreService.class);
        assertNotNull(petStoreService);
    }
}