package com.cdg.cdg_incentive_backend.module.incentivescheme.dto.response;

import com.cdg.cdg_incentive_backend.module.department.dto.response.DepartmentResponse;
import lombok.Data;

import java.util.Set;

@Data
public class IncentiveSchemeResponse {
    private Integer id;
    private String schemeKey;
    private String schemeName;
    private String targetUsing;
    private Boolean isCanCalculateShrinkgate;
    private Boolean isRequireBrandData;
    private Set<IncentiveSegmentResponse> incentiveSegments;
    private Set<DepartmentResponse> departments;
}
