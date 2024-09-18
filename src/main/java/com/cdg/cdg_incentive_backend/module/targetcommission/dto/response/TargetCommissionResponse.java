package com.cdg.cdg_incentive_backend.module.targetcommission.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TargetCommissionResponse {
    private Integer id;
    private String month;
    private String year;
    private Integer branchId;
    private String branchBU;
    private String branchNumber;
    private String branchName;
    private String branchCode;
    private BigDecimal targetCommission;
}
