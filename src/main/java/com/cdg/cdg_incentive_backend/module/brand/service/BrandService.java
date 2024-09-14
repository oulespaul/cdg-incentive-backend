package com.cdg.cdg_incentive_backend.module.brand.service;

import com.cdg.cdg_incentive_backend.module.brand.dto.request.ImportBrandRequest;

import java.util.List;

public interface BrandService {
    void importBrand(List<ImportBrandRequest> importBrandRequestList);
}
