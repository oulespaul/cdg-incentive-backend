package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TargetInHouseRequest {
    Integer brandId;
    String groupBrand;
    BigDecimal goalBrand;
    BigDecimal actualSalesIDLastYear;
}
