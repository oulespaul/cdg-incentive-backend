package com.cdg.cdg_incentive_backend.branch.service.impl;

import com.cdg.cdg_incentive_backend.branch.Branch;
import com.cdg.cdg_incentive_backend.branch.repositories.BranchRepository;
import com.cdg.cdg_incentive_backend.branch.service.BranchService;
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
