package com.cdg.cdg_incentive_backend.module.brand.dto.response;

import lombok.Data;

@Data
public class BrandResponse {
    private Integer id;
    private String brandId;
    private String brandName;
    private String departmentCode;
    private String departmentName;
    private String subDepartmentCode;
    private String subDepartmentName;
    private String creditConsign;
    private String pcType;
}
