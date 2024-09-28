package com.cdg.cdg_incentive_backend.module.targetbranch.service;

import com.cdg.cdg_incentive_backend.module.targetbranch.dto.request.CreateTargetBranchRequest;
import com.cdg.cdg_incentive_backend.module.targetbranch.dto.response.TargetBranchDetailResponse;
import com.cdg.cdg_incentive_backend.module.targetbranch.dto.response.TargetBranchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TargetBranchService {
    void createTargetBranch(CreateTargetBranchRequest createTargetBranchRequest);

    TargetBranchResponse getOneByTargetCommissionId(Integer targetCommissionId);

    Page<TargetBranchDetailResponse> getAllDetail(String year, String month, Pageable pageable);
    void deleteById(Integer targetBranchId);
}
