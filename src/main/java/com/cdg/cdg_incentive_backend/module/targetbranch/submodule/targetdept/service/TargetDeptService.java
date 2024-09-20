package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.service;

import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.entity.TargetDept;

public interface TargetDeptService {
    void save(TargetDept targetDept);
    void deleteByTargetBranchId(Integer targetBranchId);
}
