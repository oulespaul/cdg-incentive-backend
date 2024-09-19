package com.cdg.cdg_incentive_backend.module.targetbranch.repository;

import com.cdg.cdg_incentive_backend.module.targetbranch.entity.TargetBranch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TargetBranchRepository extends JpaRepository<TargetBranch, Integer> {

    Optional<TargetBranch> findByTargetCommissionId(Integer targetCommissionId);

    @Query("""
            SELECT tb
            FROM TargetBranch tb
            WHERE (:year IS NULL OR tb.targetCommission.year = :year)
            AND (:month IS NULL OR tb.targetCommission.month = :month)
            ORDER BY
            tb.targetCommission.year DESC,
            tb.targetCommission.month DESC,
            tb.targetCommission.comTgTotal DESC,
            tb.targetCommission.actualLyTotal DESC,
            tb.targetCommission.actualLyId DESC,
            tb.status DESC
            """)
    Page<TargetBranch> findAllDetail(String year, String month, Pageable pageable);
}
