package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdmm.service;

import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdmm.entity.TargetDMM;

public interface TargetDMMService {
    TargetDMM save(TargetDMM targetDMM);

    void deleteByTargetBranchId(Integer targetBranchId);
}
