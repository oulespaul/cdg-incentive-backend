package com.cdg.cdg_incentive_backend.module.brand.service.impl;

import com.cdg.cdg_incentive_backend.module.brand.dto.request.ImportBrandRequest;
import com.cdg.cdg_incentive_backend.module.brand.dto.response.BrandResponse;
import com.cdg.cdg_incentive_backend.module.brand.entity.Brand;
import com.cdg.cdg_incentive_backend.module.brand.mapper.BrandResponseMapper;
import com.cdg.cdg_incentive_backend.module.brand.repository.BrandRepository;
import com.cdg.cdg_incentive_backend.module.brand.service.BrandService;
import com.cdg.cdg_incentive_backend.module.department.entity.Department;
import com.cdg.cdg_incentive_backend.module.department.service.DepartmentService;
import com.cdg.cdg_incentive_backend.module.subdepartment.entity.SubDepartment;
import com.cdg.cdg_incentive_backend.module.subdepartment.service.SubDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final DepartmentService departmentService;
    private final SubDepartmentService subDepartmentService;
    private final BrandResponseMapper brandResponseMapper;

    @Override
    public void importBrand(List<ImportBrandRequest> importBrandRequestList) {
        List<Department> departmentList = departmentService.getAll();
        List<SubDepartment> subDepartmentList = subDepartmentService.getAll();

        List<Brand> brandList = new ArrayList<>();
        for (ImportBrandRequest request : importBrandRequestList) {
            Department departmentByCode = findDepartmentByCode(departmentList, request.getDepartmentCode());
            SubDepartment subDepartmentByCode = findSubDepartmentByCode(subDepartmentList, request.getSubDepartmentCode());

            Brand brandEntity = Brand.builder()
                    .brandId(request.getBrandId())
                    .brandName(request.getBrandName())
                    .department(departmentByCode)
                    .subDepartment(subDepartmentByCode)
                    .creditConsign(request.getCreditConsign())
                    .pcType(request.getPcType())
                    .build();
            brandList.add(brandEntity);
        }

        brandRepository.saveAll(brandList);
    }

    @Override
    public List<BrandResponse> getAllBrand() {
        List<Brand> brandList = brandRepository.findAll();
        return brandResponseMapper.fromEntityToDtoList(brandList);
    }

    private Department findDepartmentByCode(List<Department> departmentList, String departmentCode) {
        return departmentList.stream()
                .filter(department -> department.getDepartmentCode().equals(departmentCode))
                .findFirst().orElseThrow(() -> new RuntimeException("Department code not found"));
    }

    private SubDepartment findSubDepartmentByCode(List<SubDepartment> subDepartmentList, String subDepartmentCode) {
        return subDepartmentList.stream()
                .filter(department -> department.getSubDepartmentCode().equals(subDepartmentCode))
                .findFirst().orElseThrow(() -> new RuntimeException("Sub Department code not found"));
    }
}
