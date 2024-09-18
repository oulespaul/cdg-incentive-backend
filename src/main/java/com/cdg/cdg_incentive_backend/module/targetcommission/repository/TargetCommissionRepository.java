package com.cdg.cdg_incentive_backend.module.targetcommission.repository;

import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionFilterResponse;
import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionResponse;
import com.cdg.cdg_incentive_backend.module.targetcommission.entity.TargetCommission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TargetCommissionRepository extends JpaRepository<TargetCommission, Integer> {
    @Query("""
            SELECT new com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionResponse(
                tc.id as id,
                tc.month as month,
                tc.year as year,
                s.bu as branchBU,
                s.branchNumber as branchNumber,
                s.name as branchName,
                s.branchCode as branchCode,
                tc.comTgTotal as targetCommission
            )
            FROM TargetCommission tc
            JOIN tc.branch s
            WHERE (:year IS NULL OR tc.year = :year)
            AND (:month IS NULL OR tc.month = :month)
            AND (:branchNumber IS NULL OR s.branchNumber = :branchNumber)
            AND (:branchBU IS NULL OR s.bu LIKE %:branchBU%)
            AND (:branchCode IS NULL OR s.branchCode LIKE %:branchCode%)
            ORDER BY tc.createdAt DESC
            """)
    Page<TargetCommissionResponse> findAllResponse(
            String year,
            String month,
            String branchNumber,
            String branchBU,
            String branchCode,
            Pageable pageable
    );

    @Query("""
            SELECT DISTINCT tc.year
            FROM TargetCommission tc
            WHERE (:branchId IS NULL OR tc.branch.id = :branchId)
            """)
    List<String> findDistinctYearByBranchId(Integer branchId);

    @Query("""
            SELECT DISTINCT tc.month
            FROM TargetCommission tc
            WHERE (:branchId IS NULL OR tc.branch.id = :branchId)
            """)
    List<String> findDistinctMonthByBranchId(Integer branchId);

    @Query("""
            SELECT DISTINCT new com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionFilterResponse(
            CONCAT(s.branchNumber, ' - ', s.name) AS label,
            s.branchNumber AS value
            )
            FROM TargetCommission tc JOIN tc.branch s
            """)
    List<TargetCommissionFilterResponse> findDistinctBranchNumber();

    Optional<TargetCommission> findOneByYearAndMonthAndBranchId(String year, String month, Integer branchId);
}
