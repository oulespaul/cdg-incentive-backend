package com.cdg.cdg_incentive_backend.module.incentivescheme.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MajorCalculationRequest {
    private BigDecimal incentive;
}
