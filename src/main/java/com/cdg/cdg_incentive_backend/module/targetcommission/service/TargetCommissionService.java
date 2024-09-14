package com.cdg.cdg_incentive_backend.module.targetcommission.service;

import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionFilterResponse;
import com.cdg.cdg_incentive_backend.module.targetcommission.entity.TargetCommission;
import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TargetCommissionService {
    Page<TargetCommissionResponse> getAllResponse(
            String year,
            String month,
            String branchNumber,
            String branchBU,
            String branchCode,
            Integer page,
            Integer pageSize
    );

    void saveAll(List<TargetCommission> targetCommissionList);

    List<TargetCommissionFilterResponse> getDistinctYear();

    List<TargetCommissionFilterResponse> getDistinctMonth();

    List<TargetCommissionFilterResponse> getDistinctBranchNumber();
}
