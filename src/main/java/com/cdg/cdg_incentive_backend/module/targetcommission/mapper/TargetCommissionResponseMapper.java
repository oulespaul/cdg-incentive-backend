package com.cdg.cdg_incentive_backend.module.targetcommission.mapper;

import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionResponse;
import com.cdg.cdg_incentive_backend.module.targetcommission.entity.TargetCommission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TargetCommissionResponseMapper {
    @Mapping(target = "targetCommission", source = "comTgTotal")
    @Mapping(target = "branchId", source = "branch.id")
    @Mapping(target = "branchCode", source = "branch.branchCode")
    @Mapping(target = "branchName", source = "branch.name")
    @Mapping(target = "branchNumber", source = "branch.branchNumber")
    @Mapping(target = "branchBU", source = "branch.bu")
    TargetCommissionResponse fromEntityToDTO(TargetCommission targetCommission);

    List<TargetCommissionResponse> fromEntityToDTOList(List<TargetCommission> targetCommissionList);
}
