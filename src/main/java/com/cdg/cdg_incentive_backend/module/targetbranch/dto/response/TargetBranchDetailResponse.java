package com.cdg.cdg_incentive_backend.module.targetbranch.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TargetBranchDetailResponse {
    Integer id;
    String year;
    String month;
    BigDecimal targetCommission;
    BigDecimal actualSalesLyTotal;
    BigDecimal targetID;
    BigDecimal actualLyID;
    BigDecimal changeTargetCommissionPercentage;
    BigDecimal changeTargetIDPercentage;
    String status;
}
