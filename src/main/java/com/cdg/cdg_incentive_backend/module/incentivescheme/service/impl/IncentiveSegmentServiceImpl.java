package com.cdg.cdg_incentive_backend.module.incentivescheme.service.impl;

import com.cdg.cdg_incentive_backend.module.incentivescheme.dto.response.IncentiveSegmentResponse;
import com.cdg.cdg_incentive_backend.module.incentivescheme.entity.IncentiveSegment;
import com.cdg.cdg_incentive_backend.module.incentivescheme.mapper.IncentiveSegmentResponseMapper;
import com.cdg.cdg_incentive_backend.module.incentivescheme.repository.IncentiveSegmentRepository;
import com.cdg.cdg_incentive_backend.module.incentivescheme.service.IncentiveSegmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class IncentiveSegmentServiceImpl implements IncentiveSegmentService {
    private final IncentiveSegmentRepository incentiveSegmentRepository;
    private final IncentiveSegmentResponseMapper incentiveSegmentResponseMapper;

    @Override
    public List<IncentiveSegmentResponse> getAllActive() {
        List<IncentiveSegment> incentiveSegmentList = incentiveSegmentRepository.findAllActive();
        return incentiveSegmentResponseMapper.fromEntityToDtoList(incentiveSegmentList);
    }

    @Override
    public IncentiveSegment getOneById(Integer incentiveSegmentId) {
        return incentiveSegmentRepository.findById(incentiveSegmentId)
                .orElseThrow(() -> new RuntimeException("IncentiveSegment not found"));
    }

    @Override
    public List<IncentiveSegment> getAllById(List<Integer> incentiveSegmentIdList) {
        return incentiveSegmentRepository.findAllById(incentiveSegmentIdList);
    }
}
