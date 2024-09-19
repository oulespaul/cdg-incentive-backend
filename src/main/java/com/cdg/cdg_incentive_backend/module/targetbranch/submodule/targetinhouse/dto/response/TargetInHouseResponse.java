package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TargetInHouseResponse {
    private Integer id;
    private String departmentCode;
    private String departmentName;
    private String subDepartmentCode;
    private String subDepartmentName;
    private Integer brandId;
    private String brandName;
    private String groupBrand;
    private BigDecimal goalBrand;
}
