package com.jackie.study.spring.ioc;

/**
 * Created by jackiew on 6/26/2017.
 */
public class PetStoreServiceImpl implements PetStoreService {
    private PetStoreDao petStoreDao;
    private PetStoreManager petStoreManager;

    public PetStoreServiceImpl() {
    }

    public PetStoreServiceImpl(PetStoreManager petStoreManager) {
        this.petStoreManager = petStoreManager;
    }

    public PetStoreManager getPetStoreManager() {
        return petStoreManager;
    }

    public void setPetStoreManager(PetStoreManager petStoreManager) {
        this.petStoreManager = petStoreManager;
    }

    public PetStoreDao getPetStoreDao() {
        return petStoreDao;
    }

    public void setPetStoreDao(PetStoreDao petStoreDao) {
        this.petStoreDao = petStoreDao;
    }
}
