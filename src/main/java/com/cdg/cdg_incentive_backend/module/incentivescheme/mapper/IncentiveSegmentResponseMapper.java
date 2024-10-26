package com.cdg.cdg_incentive_backend.module.incentivescheme.mapper;

import com.cdg.cdg_incentive_backend.module.incentivescheme.dto.response.IncentiveSegmentResponse;
import com.cdg.cdg_incentive_backend.module.incentivescheme.entity.IncentiveSegment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IncentiveSegmentResponseMapper {
    IncentiveSegmentResponse fromEntityToDto(IncentiveSegment incentiveSegment);

    List<IncentiveSegmentResponse> fromEntityToDtoList(List<IncentiveSegment> incentiveSegmentList);
}
