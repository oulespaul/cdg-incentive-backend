package com.cdg.cdg_incentive_backend.module.authentication.repository;

import com.cdg.cdg_incentive_backend.module.authentication.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findOneByEmail(String email);
}
