package com.cdg.cdg_incentive_backend.module.employee.dto;

import lombok.Data;

@Data
public class UpdateEmployeeRequest {
    private String employeeId;
    private String newCostCenter;
    private String costCenter;
    private String employeeGroup;
    private String businessUnit;
    private String positionDescription;
    private String scheme;
    private String brandId;
    private String corporateTitle;
    private String branchNo;
    private String terminatedDate;
    private String hireDate;
    private Integer dayWorking;
}
