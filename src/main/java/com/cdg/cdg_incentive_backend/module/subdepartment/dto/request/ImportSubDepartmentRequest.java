package com.cdg.cdg_incentive_backend.module.subdepartment.dto.request;

import lombok.Data;

@Data
public class ImportSubDepartmentRequest {
    String departmentCode;
    String subDepartmentCode;
    String subDepartmentName;
}
