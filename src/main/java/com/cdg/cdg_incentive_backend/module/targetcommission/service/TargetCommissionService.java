package com.cdg.cdg_incentive_backend.module.targetcommission.service;

import com.cdg.cdg_incentive_backend.shared.dto.response.FilterResponse;
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

    TargetCommission getOneById(Integer targetCommissionID);

    void saveAll(List<TargetCommission> targetCommissionList);

    List<FilterResponse> getDistinctYearByBranchId(Integer branchId);

    List<FilterResponse> getDistinctMonthByBranchId(Integer branchId, String year);

    List<FilterResponse> getDistinctBranchNumber();
    TargetCommissionResponse getOneByYearAndMonthAndBranchId(String year, String month, Integer branchId);
}
