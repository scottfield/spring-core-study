package com.jackie.study.spring.ioc;

/**
 * Created by jackiew on 6/30/2017.
 */
public class PetStoreManagerImpl implements PetStoreManager {
    private PetStoreService petStoreService;

    public PetStoreManagerImpl() {
    }

    public PetStoreManagerImpl(PetStoreService petStoreService) {
        this.petStoreService = petStoreService;
    }

    public PetStoreService getPetStoreService() {
        return petStoreService;
    }

    public void setPetStoreService(PetStoreService petStoreService) {
        this.petStoreService = petStoreService;
    }
}
