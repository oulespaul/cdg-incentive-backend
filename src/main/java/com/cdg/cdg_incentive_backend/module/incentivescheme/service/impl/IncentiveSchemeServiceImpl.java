package com.cdg.cdg_incentive_backend.module.incentivescheme.service.impl;

import com.cdg.cdg_incentive_backend.module.department.service.DepartmentService;
import com.cdg.cdg_incentive_backend.module.incentivescheme.dto.request.CreateIncentiveSchemeRequest;
import com.cdg.cdg_incentive_backend.module.incentivescheme.dto.request.MajorCalculationRequest;
import com.cdg.cdg_incentive_backend.module.incentivescheme.dto.request.MajorGroupCalculationRequest;
import com.cdg.cdg_incentive_backend.module.incentivescheme.dto.response.IncentiveSchemeResponse;
import com.cdg.cdg_incentive_backend.module.incentivescheme.entity.IncentiveScheme;
import com.cdg.cdg_incentive_backend.module.incentivescheme.entity.IncentiveSchemeMajorCalculation;
import com.cdg.cdg_incentive_backend.module.incentivescheme.entity.IncentiveSchemeMajorGroupCalculation;
import com.cdg.cdg_incentive_backend.module.incentivescheme.mapper.IncentiveSchemeResponseMapper;
import com.cdg.cdg_incentive_backend.module.incentivescheme.repository.IncentiveSchemeRepository;
import com.cdg.cdg_incentive_backend.module.incentivescheme.service.IncentiveSchemeService;
import com.cdg.cdg_incentive_backend.module.incentivescheme.service.IncentiveSegmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class IncentiveSchemeServiceImpl implements IncentiveSchemeService {
    private final IncentiveSchemeRepository incentiveSchemeRepository;
    private final IncentiveSchemeResponseMapper incentiveSchemeResponseMapper;
    private final IncentiveSegmentService incentiveSegmentService;
    private final DepartmentService departmentService;

    @Override
    public Page<IncentiveSchemeResponse> getAllByCriteria(String schemeKey, String schemeName, Pageable pageable) {
        Page<IncentiveScheme> incentiveSchemes = incentiveSchemeRepository.findAllByCriteria(schemeKey, schemeName, pageable);
        List<IncentiveSchemeResponse> incentiveSchemeResponseList = incentiveSchemeResponseMapper.fromEntityToDtoList(incentiveSchemes.getContent());
        return new PageImpl<>(incentiveSchemeResponseList, pageable, incentiveSchemes.getTotalElements());
    }

    @Transactional
    @Override
    public IncentiveScheme createIncentiveScheme(CreateIncentiveSchemeRequest createIncentiveSchemeRequest) {
        IncentiveScheme incentiveSchemeEntity = IncentiveScheme.builder()
                .schemeKey(createIncentiveSchemeRequest.getSchemeKey())
                .schemeName(createIncentiveSchemeRequest.getSchemeName())
                .targetUsing(createIncentiveSchemeRequest.getTargetUsing())
                .isRequireBrandData(createIncentiveSchemeRequest.isRequireBrandData())
                .isCanCalculateShrinkgate(createIncentiveSchemeRequest.isCalculateShrinkgate())
                .incentiveSegments(new HashSet<>(incentiveSegmentService.getAllById(createIncentiveSchemeRequest.getSegmentIdList())))
                .departments(new HashSet<>(departmentService.getAllById(createIncentiveSchemeRequest.getDepartmentIdList())))
                .incentiveSchemeMajorGroupCalculations(new HashSet<>())
                .build();

        for (MajorGroupCalculationRequest majorGroupCalculationRequest : createIncentiveSchemeRequest.getMajorGroupCalculationList()) {
            IncentiveSchemeMajorGroupCalculation majorGroupCalculation = IncentiveSchemeMajorGroupCalculation.builder()
                    .groupName(majorGroupCalculationRequest.getGroupName())
                    .minTargetPerHead(majorGroupCalculationRequest.getMinTargetPerHead())
                    .maxTargetPerHead(majorGroupCalculationRequest.getMaxTargetPerHead())
                    .incentiveScheme(incentiveSchemeEntity)
                    .incentiveSchemeMajorCalculations(new HashSet<>())
                    .build();

            for (int i = 0; i < majorGroupCalculationRequest.getMajorCalculationList().size(); i++) {
                MajorCalculationRequest majorCalculationRequest = majorGroupCalculationRequest.getMajorCalculationList().get(i);
                IncentiveSchemeMajorCalculation majorCalculation = IncentiveSchemeMajorCalculation.builder()
                        .minIncentive(createIncentiveSchemeRequest.getMajorCalculationRangeList().get(i).getMinIncentive())
                        .maxIncentive(createIncentiveSchemeRequest.getMajorCalculationRangeList().get(i).getMaxIncentive())
                        .incentive(majorCalculationRequest.getIncentive())
                        .unit(createIncentiveSchemeRequest.getMajorCalculationUnit())
                        .incentiveSchemeMajorGroupCalculation(majorGroupCalculation)
                        .build();
                majorGroupCalculation.addIncentiveSchemeMajorCalculation(majorCalculation);
            }

            incentiveSchemeEntity.addIncentiveSchemeMajorGroupCalculation(majorGroupCalculation);
        }

        return incentiveSchemeRepository.save(incentiveSchemeEntity);
    }
}
