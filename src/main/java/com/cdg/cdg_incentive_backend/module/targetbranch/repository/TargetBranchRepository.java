package com.cdg.cdg_incentive_backend.module.targetbranch.repository;

import com.cdg.cdg_incentive_backend.module.targetbranch.entity.TargetBranch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TargetBranchRepository extends JpaRepository<TargetBranch, Integer> {

    Optional<TargetBranch> findByTargetCommissionId(Integer targetCommissionId);
}
