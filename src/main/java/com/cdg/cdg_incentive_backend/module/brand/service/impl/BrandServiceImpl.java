package com.cdg.cdg_incentive_backend.module.brand.service.impl;

import com.cdg.cdg_incentive_backend.module.brand.dto.response.BrandResponse;
import com.cdg.cdg_incentive_backend.module.brand.entity.Brand;
import com.cdg.cdg_incentive_backend.module.brand.mapper.BrandResponseMapper;
import com.cdg.cdg_incentive_backend.module.brand.repository.BrandRepository;
import com.cdg.cdg_incentive_backend.module.brand.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandResponseMapper brandResponseMapper;

    @Override
    public List<BrandResponse> getAllBrand() {
        Sort sort = Sort.by(Sort.Direction.ASC, "brandName");
        List<Brand> brandList = brandRepository.findAll(sort);
        return brandResponseMapper.fromEntityToDtoList(brandList);
    }

    @Override
    public Brand getById(Integer brandId) {
        return brandRepository.findById(brandId).orElseThrow(() -> new RuntimeException("Brand Not foundw"));
    }
}
