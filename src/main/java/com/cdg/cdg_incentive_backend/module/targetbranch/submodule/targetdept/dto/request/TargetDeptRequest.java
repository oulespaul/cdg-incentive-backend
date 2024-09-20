package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TargetDeptRequest {
    private String groupDept;
    private List<Integer> subDepartmentPool;
    private BigDecimal goalDept;
    private BigDecimal actualSalesIDLastYear;
}
