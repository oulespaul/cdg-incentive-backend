package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdmm.repository;

import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdmm.entity.TargetDMM;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TargetDMMRepository extends JpaRepository<TargetDMM, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM TargetDMM td WHERE td.targetBranch.id = :targetBranchId")
    void deleteByTargetBranchId(Integer targetBranchId);
}
