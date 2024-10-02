package com.cdg.cdg_incentive_backend.module.authentication.controller;

import com.cdg.cdg_incentive_backend.module.authentication.entity.AppUser;
import com.cdg.cdg_incentive_backend.module.authentication.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/app-user")
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping
    public ResponseEntity<AppUser> getUserDetail(@AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getClaimAsString("unique_name");
        return ResponseEntity.ok(appUserService.getByEmail(email));
    }
}