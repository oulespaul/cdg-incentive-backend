package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TargetDSMRequest {
    private String dsmId;
    private Integer departmentId;
    private Integer subDepartmentId;
    private BigDecimal goalDept;
    private BigDecimal actualSalesLastYear;
    private BigDecimal goalId;
    private BigDecimal actualSalesIDLastYear;
}