package com.cdg.cdg_incentive_backend.module.incentivescheme.service;

import com.cdg.cdg_incentive_backend.module.incentivescheme.dto.response.IncentiveSegmentResponse;

import java.util.List;

public interface IncentiveSegmentService {
    List<IncentiveSegmentResponse> getAllActive();
}
