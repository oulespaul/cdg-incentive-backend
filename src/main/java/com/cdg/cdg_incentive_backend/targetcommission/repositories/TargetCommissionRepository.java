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
                s.bu as storeBU,
                s.storeNumber as storeNumber,
                s.name as storeName,
                s.storeCode as storeCode,
                tc.comTgTotal as targetCommission
            )
            FROM TargetCommission tc
            JOIN tc.store s
            WHERE (:year IS NULL OR tc.year = :year)
            AND (:month IS NULL OR tc.month = :month)
            AND (:storeNumber IS NULL OR s.storeNumber = :storeNumber)
            AND (:storeBU IS NULL OR s.bu LIKE %:storeBU%)
            AND (:storeCode IS NULL OR s.storeCode LIKE %:storeCode%)
            ORDER BY tc.createdAt DESC
            """)
    Page<TargetCommissionResponse> findAllResponse(
            String year,
            String month,
            String storeNumber,
            String storeBU,
            String storeCode,
            Pageable pageable
    );

    @Query("SELECT DISTINCT tc.year FROM TargetCommission tc")
    List<String> findDistinctYear();

    @Query("SELECT DISTINCT tc.month FROM TargetCommission tc")
    List<String> findDistinctMonth();

    @Query("""
            SELECT DISTINCT new com.cdg.cdg_incentive_backend.targetcommission.dto.response.TargetCommissionFilterResponse(
            CONCAT(s.storeNumber, ' - ', s.name) AS label,
            s.storeNumber AS value
            )
            FROM TargetCommission tc JOIN tc.store s
            """)
    List<TargetCommissionFilterResponse> findDistinctStoreNumber();
}
