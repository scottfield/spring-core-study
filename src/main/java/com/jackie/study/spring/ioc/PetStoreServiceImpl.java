package com.jackie.study.spring.ioc;

/**
 * Created by jackiew on 6/26/2017.
 */
public class PetStoreServiceImpl implements PetStoreService {
    private PetStoreDao petStoreDao;

    public PetStoreDao getPetStoreDao() {
        return petStoreDao;
    }

    public void setPetStoreDao(PetStoreDao petStoreDao) {
        this.petStoreDao = petStoreDao;
    }
}
