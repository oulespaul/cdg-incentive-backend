package com.cdg.cdg_incentive_backend.module.incentivescheme.controller;

import com.cdg.cdg_incentive_backend.module.incentivescheme.dto.response.IncentiveSegmentResponse;
import com.cdg.cdg_incentive_backend.module.incentivescheme.service.IncentiveSegmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/incentive-segment")
@RestController
public class IncentiveSegmentController {
    private final IncentiveSegmentService incentiveSegmentService;

    @GetMapping("/active")
    public List<IncentiveSegmentResponse> getAllActive() {
        return incentiveSegmentService.getAllActive();
    }
}
