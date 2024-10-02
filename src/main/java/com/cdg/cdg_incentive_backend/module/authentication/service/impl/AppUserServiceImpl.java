package com.cdg.cdg_incentive_backend.module.authentication.service.impl;

import com.cdg.cdg_incentive_backend.module.authentication.entity.AppUser;
import com.cdg.cdg_incentive_backend.module.authentication.repository.AppUserRepository;
import com.cdg.cdg_incentive_backend.module.authentication.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;

    @Override
    public AppUser getByEmail(String email) {
        return appUserRepository.findOneByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
