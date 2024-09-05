package com.cdg.cdg_incentive_backend.targetcommission.mapper;

import com.cdg.cdg_incentive_backend.targetcommission.dto.response.TargetCommissionResponse;
import com.cdg.cdg_incentive_backend.targetcommission.entity.TargetCommission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TargetCommissionResponseMapper {
    @Mapping(target = "targetCommission", source = "comTgTotal")
    @Mapping(target = "storeCode", source = "store.storeCode")
    @Mapping(target = "storeName", source = "store.name")
    @Mapping(target = "storeNumber", source = "store.storeNumber")
    @Mapping(target = "storeBU", source = "store.bu")
    TargetCommissionResponse fromEntityToDTO(TargetCommission targetCommission);

    List<TargetCommissionResponse> fromEntityToDTOList(List<TargetCommission> targetCommissionList);
}
