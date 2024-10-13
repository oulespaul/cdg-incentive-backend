package com.cdg.cdg_incentive_backend.shared.mapper;

import com.cdg.cdg_incentive_backend.shared.dto.response.FilterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface FilterMapper {
    @Mapping(target = "value", source = "value")
    @Mapping(target = "label", source = "value")
    FilterResponse stringValueToDTO(String value);

    List<FilterResponse> stringValueToDTOList(List<String> valueList);
}
