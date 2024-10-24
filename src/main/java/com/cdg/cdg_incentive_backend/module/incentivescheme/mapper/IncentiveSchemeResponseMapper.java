package com.cdg.cdg_incentive_backend.module.incentivescheme.mapper;

import com.cdg.cdg_incentive_backend.module.incentivescheme.dto.response.IncentiveSchemeResponse;
import com.cdg.cdg_incentive_backend.module.incentivescheme.entity.IncentiveScheme;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IncentiveSchemeResponseMapper {
    IncentiveSchemeResponse fromEntityToDto(IncentiveScheme incentiveScheme);

    List<IncentiveSchemeResponse> fromEntityToDtoList(List<IncentiveScheme> incentiveSchemeList);
}
