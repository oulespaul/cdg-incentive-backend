package com.cdg.cdg_incentive_backend.module.subdepartment.mapper;

import com.cdg.cdg_incentive_backend.module.subdepartment.dto.response.SubDepartmentResponse;
import com.cdg.cdg_incentive_backend.module.subdepartment.entity.SubDepartment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SubDepartmentMapper {
    SubDepartmentResponse fromEntityToDTO(SubDepartment subDepartment);

    List<SubDepartmentResponse> fromEntityToDTOList(List<SubDepartment> subDepartment);
}
