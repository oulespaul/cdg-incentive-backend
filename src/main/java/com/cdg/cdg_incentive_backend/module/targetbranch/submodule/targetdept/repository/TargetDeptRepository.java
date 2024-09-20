package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.repository;

import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.entity.TargetDept;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TargetDeptRepository extends JpaRepository<TargetDept, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM TargetDept t WHERE t.targetBranch.id = :targetBranchId")
    void deleteByTargetBranchId(Integer targetBranchId);
}
