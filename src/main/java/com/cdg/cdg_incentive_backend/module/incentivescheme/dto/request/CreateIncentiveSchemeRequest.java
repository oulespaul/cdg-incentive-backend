package com.cdg.cdg_incentive_backend.module.incentivescheme.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateIncentiveSchemeRequest {
    private String schemeName;
    private String schemeKey;
    private String targetUsing;
    private List<Integer> segmentIdList;
    private List<Integer> departmentIdList;
    private List<String> stepCalculationList;
    private String isCalculateShrinkgate;
    private String isRequireBrandData;
    private List<MajorGroupCalculationRequest> majorGroupCalculationList;
    private String majorCalculationUnit;
    private List<MajorCalculationRangeRequest> majorCalculationRangeList;

    public Boolean isCalculateShrinkgate() {
        return "CALCULATE".equals(isCalculateShrinkgate);
    }

    public Boolean isRequireBrandData() {
        return "REQUIRED".equals(isRequireBrandData);
    }
}
