package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.service.impl;

import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.entity.TargetInHouse;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.repository.TargetInHouseRepository;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.service.TargetInHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TargetInHouseServiceImpl implements TargetInHouseService {
    private final TargetInHouseRepository targetInHouseRepository;

    @Override
    public void save(TargetInHouse targetInHouse) {
        targetInHouseRepository.save(targetInHouse);
    }

    @Override
    public void deleteByTargetBranchId(Integer targetBranchId) {
        targetInHouseRepository.deleteByTargetBranchId(targetBranchId);
    }
}
