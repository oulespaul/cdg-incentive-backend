package com.cdg.cdg_incentive_backend.module.brand.dto.request;

import lombok.Data;

@Data
public class ImportBrandRequest {
    private String brandId;
    private String brandName;
    private String departmentCode;
    private String subDepartmentCode;
    private String creditConsign;
    private String pcType;
}
