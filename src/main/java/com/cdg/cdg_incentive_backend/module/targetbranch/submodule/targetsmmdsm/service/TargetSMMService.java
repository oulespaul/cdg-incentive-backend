package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.service;

import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.entity.TargetSMM;

public interface TargetSMMService {
    TargetSMM save(TargetSMM targetSMM);
    void deleteByTargetBranchId(Integer targetBranchId);
}
