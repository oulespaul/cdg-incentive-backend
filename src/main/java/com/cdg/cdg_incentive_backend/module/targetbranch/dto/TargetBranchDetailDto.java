package com.cdg.cdg_incentive_backend.module.targetbranch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TargetBranchDetailDto {
    Integer id;
    String status;
    String year;
    String month;
    BigDecimal targetCommission;
    BigDecimal actualSalesLyTotal;
    BigDecimal targetID;
    BigDecimal actualLyID;
}
