package com.cdg.cdg_incentive_backend.targetcommission.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TargetCommissionResponse {
    private Integer id;
    private String month;
    private String year;
    private String storeBU;
    private String storeNumber;
    private String storeName;
    private String storeCode;
    private BigDecimal targetCommission;
}
