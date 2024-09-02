package com.cdg.cdg_incentive_backend.targetcommission.repositories;

import com.cdg.cdg_incentive_backend.targetcommission.dto.response.TargetCommissionResponse;
import com.cdg.cdg_incentive_backend.targetcommission.entity.TargetCommission;
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
            ) FROM TargetCommission tc JOIN tc.store s
            """)
    List<TargetCommissionResponse> findAllResponse();
}
