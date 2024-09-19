package com.cdg.cdg_incentive_backend.module.targetbranch.dto.response;

import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.dto.response.TargetInHouseResponse;
import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionResponse;
import com.cdg.cdg_incentive_backend.module.targetcommission.entity.TargetCommission;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class TargetBranchResponse {
    private Integer id;
    private String status;
    private LocalDateTime requestedAt;
    private String requestedBy;
    private LocalDateTime approvedAt;
    private String approvedBy;
    private LocalDateTime rejectedAt;
    private String rejectedBy;
    private String rejectedReason;
    private LocalDateTime calculatedAt;
    private String calculatedBy;
    private TargetCommissionResponse targetCommission;
    private Set<TargetInHouseResponse> targetInHouseList;
}
