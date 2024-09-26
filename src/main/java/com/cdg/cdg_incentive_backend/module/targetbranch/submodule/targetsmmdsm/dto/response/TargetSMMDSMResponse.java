package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class TargetSMMDSMResponse {
    private String smmId;
    private List<TargetDSMResponse> targetDSMList;
}
