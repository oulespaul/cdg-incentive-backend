package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.service.impl;

import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.entity.TargetDSM;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.repository.TargetDSMRepository;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.service.TargetDSMService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TargetDSMServiceImpl implements TargetDSMService {
    private final TargetDSMRepository targetDSMRepository;

    public TargetDSM save(TargetDSM targetDSM) {
        return targetDSMRepository.save(targetDSM);
    }
}
