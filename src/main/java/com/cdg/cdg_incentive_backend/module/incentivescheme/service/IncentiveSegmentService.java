package com.cdg.cdg_incentive_backend.module.incentivescheme.service;

import com.cdg.cdg_incentive_backend.module.incentivescheme.dto.response.IncentiveSegmentResponse;
import com.cdg.cdg_incentive_backend.module.incentivescheme.entity.IncentiveScheme;
import com.cdg.cdg_incentive_backend.module.incentivescheme.entity.IncentiveSegment;

import java.util.List;

public interface IncentiveSegmentService {
    List<IncentiveSegmentResponse> getAllActive();

    IncentiveSegment getOneById(Integer incentiveSegmentId);
    List<IncentiveSegment> getAllById(List<Integer> incentiveSegmentIdList);
}
