package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.dto.request.response;

import com.cdg.cdg_incentive_backend.module.subdepartment.dto.response.SubDepartmentResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class TargetDeptResponse {
    private Integer id;
    private String groupDept;
    private Set<SubDepartmentResponse> subDepartmentPool;
    private BigDecimal goalDept;
    private BigDecimal actualSalesIDLastYear;
}
