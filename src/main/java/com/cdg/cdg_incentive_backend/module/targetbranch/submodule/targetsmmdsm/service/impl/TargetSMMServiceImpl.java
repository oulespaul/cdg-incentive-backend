package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.service.impl;

import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.entity.TargetSMM;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.repository.TargetSMMRepository;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.service.TargetSMMService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TargetSMMServiceImpl implements TargetSMMService {
    private final TargetSMMRepository targetSMMRepository;

    public TargetSMM save(TargetSMM targetSMM) {
        return targetSMMRepository.save(targetSMM);
    }

    @Override
    public void deleteByTargetBranchId(Integer targetBranchId) {
        targetSMMRepository.deleteByTargetBranchId(targetBranchId);
    }
}
