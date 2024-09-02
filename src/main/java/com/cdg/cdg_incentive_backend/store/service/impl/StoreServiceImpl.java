package com.cdg.cdg_incentive_backend.store.service.impl;

import com.cdg.cdg_incentive_backend.store.Store;
import com.cdg.cdg_incentive_backend.store.repositories.StoreRepository;
import com.cdg.cdg_incentive_backend.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;

    @Override
    public List<Store> getAll() {
        return storeRepository.findAll();
    }
}
