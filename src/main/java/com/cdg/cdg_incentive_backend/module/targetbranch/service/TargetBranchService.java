package com.cdg.cdg_incentive_backend.module.targetbranch.service;

import com.cdg.cdg_incentive_backend.module.targetbranch.dto.request.CreateTargetBranchRequest;
import com.cdg.cdg_incentive_backend.module.targetbranch.dto.response.TargetBranchResponse;

public interface TargetBranchService {
    void createTargetBranch(CreateTargetBranchRequest createTargetBranchRequest);
    TargetBranchResponse getOneByTargetCommissionId(Integer targetCommissionId);
}
