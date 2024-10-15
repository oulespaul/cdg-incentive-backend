package com.cdg.cdg_incentive_backend.shared.service.impl;

import com.cdg.cdg_incentive_backend.shared.dto.AppUserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;

public class BaseSessionService {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    protected AppUserInfo getAppUserSessionClaimsInfo() {
        Authentication authentication = this.getAuthentication();
        Map<String, Object> claims = ((Jwt) authentication.getPrincipal()).getClaims();
        return convertClaimsToDTO(claims);
    }

    private AppUserInfo convertClaimsToDTO(Map<String, Object> claims) {
        AppUserInfo dto = new AppUserInfo();
        dto.setUsername((String) claims.get("name"));
        return dto;
    }
}
