package com.cdg.cdg_incentive_backend.module.targetbranch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TargetBranchDetailDto {
    private Integer id;
    private String status;
    private String year;
    private String month;
    private String branchBU;
    private String branchNumber;
    private String branchName;
    private String branchCode;
    private BigDecimal targetCommission;
    private BigDecimal actualSalesLyTotal;
    private BigDecimal targetID;
    private BigDecimal actualLyID;
}
