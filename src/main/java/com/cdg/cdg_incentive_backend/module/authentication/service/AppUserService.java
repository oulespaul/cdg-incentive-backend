package com.cdg.cdg_incentive_backend.module.authentication.service;

import com.cdg.cdg_incentive_backend.module.authentication.entity.AppUser;

public interface AppUserService {
    AppUser getByEmail(String email);
}
