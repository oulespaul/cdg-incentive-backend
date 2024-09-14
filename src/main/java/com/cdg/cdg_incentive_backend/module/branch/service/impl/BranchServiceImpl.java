package com.cdg.cdg_incentive_backend.module.branch.service.impl;

import com.cdg.cdg_incentive_backend.module.branch.entity.Branch;
import com.cdg.cdg_incentive_backend.module.branch.repositories.BranchRepository;
import com.cdg.cdg_incentive_backend.module.branch.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;

    @Override
    public List<Branch> getAll() {
        return branchRepository.findAll();
    }
}
