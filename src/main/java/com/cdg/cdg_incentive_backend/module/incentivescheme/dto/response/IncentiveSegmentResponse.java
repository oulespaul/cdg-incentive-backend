package com.cdg.cdg_incentive_backend.module.incentivescheme.dto.response;

import lombok.Data;

@Data
public class IncentiveSegmentResponse {
    private Integer id;
    private String name;
    private Boolean isActive;
}
