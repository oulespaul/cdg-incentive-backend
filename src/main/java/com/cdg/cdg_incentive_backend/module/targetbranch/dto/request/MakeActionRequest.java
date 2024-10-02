package com.cdg.cdg_incentive_backend.module.targetbranch.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class MakeActionRequest {
    private List<Integer> targetBranchIdList;
    private String action;
    private String rejectReason;
}
