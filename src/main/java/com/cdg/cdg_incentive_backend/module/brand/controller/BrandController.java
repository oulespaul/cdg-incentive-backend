package com.cdg.cdg_incentive_backend.module.brand.controller;

import com.cdg.cdg_incentive_backend.module.brand.dto.request.ImportBrandRequest;
import com.cdg.cdg_incentive_backend.module.brand.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandService brandService;

    @PostMapping("/import-data")
    ResponseEntity<Boolean> importBrandData(
            @RequestBody List<ImportBrandRequest> importBrandRequestList
    ) {
        brandService.importBrand(importBrandRequestList);
        return ResponseEntity.ok(true);
    }
}
