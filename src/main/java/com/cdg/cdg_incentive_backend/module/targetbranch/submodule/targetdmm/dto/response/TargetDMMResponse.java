package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdmm.dto.response;

import com.cdg.cdg_incentive_backend.module.department.dto.response.DepartmentResponse;
import com.cdg.cdg_incentive_backend.module.subdepartment.dto.response.SubDepartmentResponse;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TargetDMMResponse {
    private String dmmId;
    private DepartmentResponse department;
    private SubDepartmentResponse subDepartment;
    private BigDecimal goalDept;
    private BigDecimal actualSalesLastYear;
    private BigDecimal goalId;
    private BigDecimal actualSalesIDLastYear;
}
