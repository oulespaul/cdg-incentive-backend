package com.cdg.cdg_incentive_backend.module.targetbranch.repository;

import com.cdg.cdg_incentive_backend.module.targetbranch.dto.TargetBranchDetailDto;
import com.cdg.cdg_incentive_backend.module.targetbranch.entity.TargetBranch;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TargetBranchRepository extends JpaRepository<TargetBranch, Integer> {

    Optional<TargetBranch> findByTargetCommissionId(Integer targetCommissionId);

    @Query(value = """
            SELECT new com.cdg.cdg_incentive_backend.module.targetbranch.dto.TargetBranchDetailDto(
                tb.id as id,
                tb.status as status,
                tc.year as year,
                tc.month as month,
                tc.branch.bu as branchBU,
                tc.branch.branchNumber as branchNumber,
                tc.branch.name as branchName,
                tc.branch.branchCode as branchCode,
                COALESCE(SUM(tc.comTgTotal), 0) as targetCommission,
                COALESCE(SUM(tds.actualSalesLastYear), 0) + COALESCE(SUM(tdm.actualSalesLastYear), 0) as actualSalesLyTotal,
                COALESCE(SUM(tds.goalId), 0) + COALESCE(SUM(tdm.goalId), 0) as targetID,
                COALESCE(SUM(tih.actualSalesIDLastYear), 0) + COALESCE(SUM(td.actualSalesIDLastYear), 0) as actualLyID
            )
            FROM TargetBranch tb
            JOIN tb.targetCommission tc
            LEFT JOIN tb.targetSMMs ts
            LEFT JOIN tb.targetSMMs.targetDSMs tds
            LEFT JOIN tb.targetDMMs tdm
            LEFT JOIN tb.targetInHouses tih
            LEFT JOIN tb.targetDept td
            WHERE (:year IS NULL OR tc.year = :year)
            AND (:month IS NULL OR tc.month = :month)
            AND (:status IS NULL OR tb.status = :status)
            AND (:branchNumber IS NULL OR tc.branch.branchNumber = :branchNumber)
            AND (:branchBU IS NULL OR tc.branch.bu = :branchBU)
            AND (:branchCode IS NULL OR tc.branch.branchCode = :branchCode)
            GROUP BY tb.id,tb.status,tc.year,tc.month,tc.branch.bu,tc.branch.branchNumber,tc.branch.name,tc.branch.branchCode
            ORDER BY
            year DESC,
            month DESC,
            SUM(tc.comTgTotal) DESC,
            COALESCE(SUM(tds.actualSalesLastYear), 0) + COALESCE(SUM(tdm.actualSalesLastYear), 0) DESC,
            COALESCE(SUM(tds.goalId), 0) + COALESCE(SUM(tdm.goalId), 0) DESC,
            COALESCE(SUM(tih.actualSalesIDLastYear), 0) + COALESCE(SUM(td.actualSalesIDLastYear), 0) DESC
            """,
            countQuery = """
                    SELECT COUNT(tb.id)
                    FROM TargetBranch tb
                    JOIN tb.targetCommission tc
                    WHERE (:year IS NULL OR tc.year = :year)
                    AND (:month IS NULL OR tc.month = :month)
                    AND (:status IS NULL OR tb.status = :status)
                    AND (:branchNumber IS NULL OR tc.branch.branchNumber = :branchNumber)
                    AND (:branchBU IS NULL OR tc.branch.bu = :branchBU)
                    AND (:branchCode IS NULL OR tc.branch.branchCode = :branchCode)
                    GROUP BY tb.id,tb.status,tc.year,tc.month,tc.branch.bu,tc.branch.branchNumber,tc.branch.name,tc.branch.branchCode
                    """)
    Page<TargetBranchDetailDto> findAllDetail(
            String year,
            String month,
            String status,
            String branchNumber,
            String branchBU,
            String branchCode,
            Pageable pageable);

    @Transactional
    @Modifying
    @Query("DELETE FROM TargetBranch tb WHERE tb.id = :targetBranchId")
    void deleteById(Integer targetBranchId);
}
