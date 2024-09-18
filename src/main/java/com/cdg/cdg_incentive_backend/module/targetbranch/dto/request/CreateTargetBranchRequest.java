package com.cdg.cdg_incentive_backend.module.targetbranch.dto.request;

import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.dto.request.TargetInHouseRequest;
import lombok.Data;

import java.util.List;

@Data
public class CreateTargetBranchRequest {
    Integer targetCommissionId;
    Integer branchId;
    List<TargetInHouseRequest> targetInHouseList;
}
