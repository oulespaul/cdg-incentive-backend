package com.cdg.cdg_incentive_backend.module.targetbranch.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TargetBranchDetailResponse {
    private Integer id;
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
    private BigDecimal changeTargetCommissionPercentage;
    private BigDecimal changeTargetIDPercentage;
    private String status;
}
