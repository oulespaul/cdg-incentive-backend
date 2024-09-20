package com.cdg.cdg_incentive_backend.module.targetbranch.mapper;

import com.cdg.cdg_incentive_backend.module.subdepartment.dto.response.SubDepartmentResponse;
import com.cdg.cdg_incentive_backend.module.subdepartment.entity.SubDepartment;
import com.cdg.cdg_incentive_backend.module.targetbranch.dto.response.TargetBranchResponse;
import com.cdg.cdg_incentive_backend.module.targetbranch.entity.TargetBranch;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.dto.request.response.TargetDeptResponse;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.entity.TargetDept;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.dto.response.TargetInHouseResponse;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.entity.TargetInHouse;
import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionResponse;
import com.cdg.cdg_incentive_backend.module.targetcommission.entity.TargetCommission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TargetBranchResponseMapper {
    @Mapping(source = "targetDept", target = "targetDeptList")
    @Mapping(source = "targetInHouses", target = "targetInHouseList")
    @Mapping(source = "targetCommission", target = "targetCommission")
    TargetBranchResponse fromEntityToDto(TargetBranch targetBranch);

    @Mapping(target = "targetCommission", source = "comTgTotal")
    @Mapping(target = "branchId", source = "branch.id")
    @Mapping(target = "branchCode", source = "branch.branchCode")
    @Mapping(target = "branchName", source = "branch.name")
    @Mapping(target = "branchNumber", source = "branch.branchNumber")
    @Mapping(target = "branchBU", source = "branch.bu")
    TargetCommissionResponse toTargetCommissionDto(TargetCommission targetCommission);

    @Mapping(target = "brandName", source = "brand.brandName")
    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "subDepartmentName", source = "brand.subDepartment.subDepartmentName")
    @Mapping(target = "subDepartmentCode", source = "brand.subDepartment.subDepartmentCode")
    @Mapping(target = "departmentName", source = "brand.department.departmentName")
    @Mapping(target = "departmentCode", source = "brand.department.departmentCode")
    TargetInHouseResponse toTargetInHouseDto(TargetInHouse targetInHouse);

    @Mapping(target = "subDepartmentPool", source = "deptPool")
    TargetDeptResponse toTargetDeptDto(TargetDept targetDept);

    SubDepartmentResponse toSubDepartmentDto(SubDepartment subDepartment);
}
