package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.repository;

import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.entity.TargetSMM;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TargetSMMRepository extends JpaRepository<TargetSMM, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM TargetSMM ts WHERE ts.targetBranch.id = :targetBranchId")
    void deleteByTargetBranchId(Integer targetBranchId);
}
