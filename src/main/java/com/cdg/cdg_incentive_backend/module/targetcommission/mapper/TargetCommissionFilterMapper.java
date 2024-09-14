package com.cdg.cdg_incentive_backend.module.targetcommission.mapper;

import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionFilterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TargetCommissionFilterMapper {
    @Mapping(target = "value", source = "value")
    @Mapping(target = "label", source = "value")
    TargetCommissionFilterResponse stringValueToDTO(String value);

    List<TargetCommissionFilterResponse> stringValueToDTOList(List<String> valueList);
}
