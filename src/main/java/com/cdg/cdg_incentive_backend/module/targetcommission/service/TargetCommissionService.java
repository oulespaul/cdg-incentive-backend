package com.cdg.cdg_incentive_backend.module.targetcommission.service;

import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionFilterResponse;
import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionResponse;
import com.cdg.cdg_incentive_backend.module.targetcommission.entity.TargetCommission;
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

    List<TargetCommissionFilterResponse> getDistinctYearByBranchId(Integer branchId);

    List<TargetCommissionFilterResponse> getDistinctMonthByBranchId(Integer branchId);

    List<TargetCommissionFilterResponse> getDistinctBranchNumber();
}
