package com.cdg.cdg_incentive_backend.targetcommission.repositories;

import com.cdg.cdg_incentive_backend.targetcommission.dto.response.TargetCommissionFilterResponse;
import com.cdg.cdg_incentive_backend.targetcommission.dto.response.TargetCommissionResponse;
import com.cdg.cdg_incentive_backend.targetcommission.entity.TargetCommission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TargetCommissionRepository extends JpaRepository<TargetCommission, Integer> {
    @Query("""
            SELECT new com.cdg.cdg_incentive_backend.targetcommission.dto.response.TargetCommissionResponse(
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

    @Query("SELECT DISTINCT tc.year FROM TargetCommission tc")
    List<String> findDistinctYear();

    @Query("SELECT DISTINCT tc.month FROM TargetCommission tc")
    List<String> findDistinctMonth();

    @Query("""
            SELECT DISTINCT new com.cdg.cdg_incentive_backend.targetcommission.dto.response.TargetCommissionFilterResponse(
            CONCAT(s.branchNumber, ' - ', s.name) AS label,
            s.branchNumber AS value
            )
            FROM TargetCommission tc JOIN tc.branch s
            """)
    List<TargetCommissionFilterResponse> findDistinctBranchNumber();
}
