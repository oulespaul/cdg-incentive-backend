package com.cdg.cdg_incentive_backend.targetcommission.dto.response;

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
    private String bu;
    private String branchNo;
    private String branchName;
    private String branchCode;
    private BigDecimal targetCommission;
}
