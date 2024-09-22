package com.cdg.cdg_incentive_backend.module.brand.service;

import com.cdg.cdg_incentive_backend.module.brand.dto.response.BrandResponse;
import com.cdg.cdg_incentive_backend.module.brand.entity.Brand;

import java.util.List;

public interface BrandService {
    List<BrandResponse> getAllBrand();

    Brand getById(Integer brandId);
}
