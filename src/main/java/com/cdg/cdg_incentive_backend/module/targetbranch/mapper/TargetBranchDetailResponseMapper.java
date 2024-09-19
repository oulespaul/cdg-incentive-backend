package com.cdg.cdg_incentive_backend.module.targetbranch.mapper;

import com.cdg.cdg_incentive_backend.module.targetbranch.dto.response.TargetBranchDetailResponse;
import com.cdg.cdg_incentive_backend.module.targetbranch.entity.TargetBranch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TargetBranchDetailResponseMapper {
    @Mapping(source = "targetCommission.actualLyId", target = "actualLyID")
    @Mapping(source = "targetCommission.actualLyTotal", target = "actualSalesLyTotal")
    @Mapping(source = "targetCommission.comTgTotal", target = "targetCommission")
    @Mapping(source = "targetCommission.month", target = "month")
    @Mapping(source = "targetCommission.year", target = "year")
    TargetBranchDetailResponse fromEntityToDto(TargetBranch targetBranch);

    List<TargetBranchDetailResponse> fromEntityToDtoList(List<TargetBranch> targetBranch);
}
