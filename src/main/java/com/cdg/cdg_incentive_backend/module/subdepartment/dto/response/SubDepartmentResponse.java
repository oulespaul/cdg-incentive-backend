package com.cdg.cdg_incentive_backend.module.subdepartment.dto.response;

import lombok.Data;

@Data
public class SubDepartmentResponse {
    private Integer id;
    private Integer departmentId;
    private String subDepartmentCode;
    private String subDepartmentName;
}
