package com.cdg.cdg_incentive_backend.module.incentivescheme.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MajorGroupCalculationRequest {
    private String groupName;
    private BigDecimal minTargetPerHead;
    private BigDecimal maxTargetPerHead;
    private List<MajorCalculationRequest> majorCalculationList;
}
