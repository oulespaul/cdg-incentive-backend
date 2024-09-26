package com.cdg.cdg_incentive_backend.module.targetbranch.dto.response;

import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.dto.response.TargetDeptResponse;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.dto.response.TargetInHouseResponse;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.dto.response.TargetSMMDSMResponse;
import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionResponse;
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
    private Set<TargetDeptResponse> targetDeptList;
    private Set<TargetSMMDSMResponse> targetSMMDSMList;
}
