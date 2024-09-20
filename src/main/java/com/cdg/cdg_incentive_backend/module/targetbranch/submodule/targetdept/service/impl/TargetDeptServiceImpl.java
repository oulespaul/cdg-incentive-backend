package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.service.impl;

import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.entity.TargetDept;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.repository.TargetDeptRepository;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.service.TargetDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TargetDeptServiceImpl implements TargetDeptService {
    private final TargetDeptRepository targetDeptRepository;

    @Override
    public void save(TargetDept targetDept) {
        targetDeptRepository.save(targetDept);
    }

    @Override
    public void deleteByTargetBranchId(Integer targetBranchId) {
        targetDeptRepository.deleteByTargetBranchId(targetBranchId);
    }
}
