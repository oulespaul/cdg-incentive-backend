package com.cdg.cdg_incentive_backend.targetcommission.service;

import com.cdg.cdg_incentive_backend.targetcommission.dto.response.TargetCommissionFilterResponse;
import com.cdg.cdg_incentive_backend.targetcommission.dto.response.TargetCommissionResponse;
import com.cdg.cdg_incentive_backend.targetcommission.entity.TargetCommission;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TargetCommissionService {
    Page<TargetCommissionResponse> getAllResponse(
            String year,
            String month,
            String storeNumber,
            String storeBU,
            String storeCode,
            Integer page,
            Integer pageSize
    );

    void saveAll(List<TargetCommission> targetCommissionList);

    List<TargetCommissionFilterResponse> getDistinctYear();

    List<TargetCommissionFilterResponse> getDistinctMonth();

    List<TargetCommissionFilterResponse> getDistinctStoreNumber();
}
