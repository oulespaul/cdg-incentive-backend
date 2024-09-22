package com.cdg.cdg_incentive_backend.module.department.mapper;

import com.cdg.cdg_incentive_backend.module.department.dto.response.DepartmentResponse;
import com.cdg.cdg_incentive_backend.module.department.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DepartmentMapper {
    DepartmentResponse fromEntityToDTO(Department department);

    List<DepartmentResponse> fromEntityToDTOList(List<Department> departmentList);
}
