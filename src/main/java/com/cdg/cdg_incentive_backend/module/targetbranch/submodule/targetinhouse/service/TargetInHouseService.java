package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.service;

import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.entity.TargetInHouse;

public interface TargetInHouseService {
    void save(TargetInHouse targetInHouse);

    void deleteByTargetBranchId(Integer targetBranchId);
}
