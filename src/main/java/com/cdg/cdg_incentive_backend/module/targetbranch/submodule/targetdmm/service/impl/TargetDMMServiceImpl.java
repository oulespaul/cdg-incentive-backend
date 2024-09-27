package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdmm.service.impl;

import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdmm.entity.TargetDMM;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdmm.repository.TargetDMMRepository;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdmm.service.TargetDMMService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TargetDMMServiceImpl implements TargetDMMService {
    private final TargetDMMRepository targetDMMRepository;

    public TargetDMM save(TargetDMM targetDMM) {
        return targetDMMRepository.save(targetDMM);
    }

    @Override
    public void deleteByTargetBranchId(Integer targetBranchId) {
        targetDMMRepository.deleteByTargetBranchId(targetBranchId);
    }
}
