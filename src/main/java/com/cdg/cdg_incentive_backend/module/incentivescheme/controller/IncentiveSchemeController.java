package com.cdg.cdg_incentive_backend.module.incentivescheme.controller;

import com.cdg.cdg_incentive_backend.module.incentivescheme.dto.response.IncentiveSchemeResponse;
import com.cdg.cdg_incentive_backend.module.incentivescheme.service.IncentiveSchemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/incentive-scheme")
@RestController
public class IncentiveSchemeController {
    private final IncentiveSchemeService incentiveSchemeService;

    @GetMapping
    public Page<IncentiveSchemeResponse> getAll(
            @RequestParam(name = "schemeKey", required = false) String schemeKey,
            @RequestParam(name = "schemeName", required = false) String schemeName,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return incentiveSchemeService.getAllByCriteria(schemeKey, schemeName, pageable);
    }
}
