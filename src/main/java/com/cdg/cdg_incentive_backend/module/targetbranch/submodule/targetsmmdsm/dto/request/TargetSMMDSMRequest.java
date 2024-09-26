package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class TargetSMMDSMRequest {
    private String smmId;
    private List<TargetDSMRequest> targetDSMList;
}
