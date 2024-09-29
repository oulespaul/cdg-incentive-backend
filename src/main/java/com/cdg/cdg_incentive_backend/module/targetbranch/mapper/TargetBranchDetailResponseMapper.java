package com.cdg.cdg_incentive_backend.module.targetbranch.mapper;

import com.cdg.cdg_incentive_backend.module.targetbranch.dto.TargetBranchDetailDto;
import com.cdg.cdg_incentive_backend.module.targetbranch.dto.response.TargetBranchDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TargetBranchDetailResponseMapper {
    @Mapping(target = "changeTargetCommissionPercentage",
            expression = "java(calChangeTargetCommissionPercentage(targetBranch))")
    @Mapping(target = "changeTargetIDPercentage",
            expression = "java(calChangeTargetIDPercentage(targetBranch))")
    TargetBranchDetailResponse fromEntityToDto(TargetBranchDetailDto targetBranch);

    List<TargetBranchDetailResponse> fromEntityToDtoList(List<TargetBranchDetailDto> targetBranch);

    default BigDecimal calChangeTargetCommissionPercentage(TargetBranchDetailDto targetBranch) {
        if (targetBranch.getActualSalesLyTotal().compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return ((targetBranch.getTargetCommission().subtract(targetBranch.getActualSalesLyTotal()))
                .divide(targetBranch.getActualSalesLyTotal(), RoundingMode.HALF_UP)).multiply(new BigDecimal(100));
    }

    default BigDecimal calChangeTargetIDPercentage(TargetBranchDetailDto targetBranch) {
        if (targetBranch.getActualLyID().compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return ((targetBranch.getTargetID().subtract(targetBranch.getActualLyID()))
                .divide(targetBranch.getActualLyID(), RoundingMode.HALF_UP)).multiply(new BigDecimal(100));
    }
}
