package com.jackie.study.spring.ioc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jackiew on 6/26/2017.
 */
public class ApplicationContextTest {
    private ClassPathXmlApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
    }

    @After
    public void tearDown() throws Exception {
        context.close();
        context = null;
    }

    @Test
    public void applicationInitializationTest() throws Exception {
        PetStoreService petStoreService = context.getBean(PetStoreService.class);
        assertNotNull(petStoreService);
        System.out.println(petStoreService);
    }

    @Test
    public void testCircularReference() throws Exception {
        PetStoreService petStoreService = context.getBean(PetStoreService.class);
        assertNotNull(petStoreService);
        System.out.println(petStoreService);
    }

    @Test
    public void testBeanScope() throws Exception {
        SingletonBean singletonBean = (SingletonBean) context.getBean("singletonBean");
        assertNotNull(singletonBean);
        SingletonBean anotherSingletonBean = (SingletonBean) context.getBean("singletonBean");
        assertNotNull(anotherSingletonBean);
        assertEquals(singletonBean, anotherSingletonBean);
        assertTrue(singletonBean == anotherSingletonBean);
        assertFalse(singletonBean.getPrototypeBean() == anotherSingletonBean.getPrototypeBean());
    }

    @Test
    public void testMethodInject() throws Exception {
        SingletonBean singletonBean = (SingletonBean) context.getBean("singletonBean");
        assertNotNull(singletonBean);
        SingletonBean anotherSingletonBean = (SingletonBean) context.getBean("singletonBean");
        assertNotNull(anotherSingletonBean);
        assertEquals(singletonBean, anotherSingletonBean);
        assertTrue(singletonBean == anotherSingletonBean);
        assertFalse(singletonBean.injectPrototypeBean() == anotherSingletonBean.injectPrototypeBean());
    }

    @Test
    public void testAliasBean() throws Exception {
        PetStoreManagerImpl petStoreManager = (PetStoreManagerImpl) context.getBean("petStoreManager");
        PetStoreManagerImpl anotherPetStoreManager = (PetStoreManagerImpl) context.getBean("anotherPetStoreManager");
        assertNotNull(petStoreManager);
        assertNotNull(anotherPetStoreManager);
        assertTrue(petStoreManager.getPetStoreService() == anotherPetStoreManager.getPetStoreService());
    }

    @Test
    public void testPropertyPlaceholder() throws Exception {
        PlaceHolderBean placeHolderBean = (PlaceHolderBean) context.getBean("placeHolderBean");
        assertNotNull(placeHolderBean);
        assertEquals("jackie", placeHolderBean.getName());
        assertEquals("male", placeHolderBean.getSex());
    }

    @Test
    public void testPropertyOverride() throws Exception {
        PlaceHolderBean placeHolderBean = (PlaceHolderBean) context.getBean("placeHolderBean");
        assertNotNull(placeHolderBean);
        assertEquals("jackie", placeHolderBean.getName());
        assertEquals("unknown", placeHolderBean.getSex());
    }
}