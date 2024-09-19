package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.repository;

import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.entity.TargetInHouse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TargetInHouseRepository extends JpaRepository<TargetInHouse, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM TargetInHouse t WHERE t.targetBranch.id = :targetBranchId")
    void deleteByTargetBranchId(Integer targetBranchId);
}
