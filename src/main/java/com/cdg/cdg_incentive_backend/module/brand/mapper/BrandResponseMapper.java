package com.cdg.cdg_incentive_backend.module.brand.mapper;

import com.cdg.cdg_incentive_backend.module.brand.dto.response.BrandResponse;
import com.cdg.cdg_incentive_backend.module.brand.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BrandResponseMapper {
    @Mapping(target = "subDepartmentName", source = "subDepartment.subDepartmentName")
    @Mapping(target = "subDepartmentCode", source = "subDepartment.subDepartmentCode")
    @Mapping(target = "departmentName", source = "department.departmentName")
    @Mapping(target = "departmentCode", source = "department.departmentCode")
    BrandResponse fromEntityToDto(Brand brand);

    List<BrandResponse> fromEntityToDtoList(List<Brand> brandList);
}
