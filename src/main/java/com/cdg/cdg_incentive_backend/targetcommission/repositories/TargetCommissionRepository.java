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
                'CDS' as bu,
                b.branchNo as branchNo,
                b.branchName as branchName,
                b.branchCode as branchCode,
                tc.comTgTotal as targetCommission
            ) FROM TargetCommission tc JOIN tc.branch b
            """)
    List<TargetCommissionResponse> findAllResponse();
}
