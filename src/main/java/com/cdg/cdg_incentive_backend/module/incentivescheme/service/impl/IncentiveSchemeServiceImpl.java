package com.cdg.cdg_incentive_backend.module.incentivescheme.service.impl;

import com.cdg.cdg_incentive_backend.module.incentivescheme.dto.response.IncentiveSchemeResponse;
import com.cdg.cdg_incentive_backend.module.incentivescheme.entity.IncentiveScheme;
import com.cdg.cdg_incentive_backend.module.incentivescheme.mapper.IncentiveSchemeResponseMapper;
import com.cdg.cdg_incentive_backend.module.incentivescheme.repository.IncentiveSchemeRepository;
import com.cdg.cdg_incentive_backend.module.incentivescheme.service.IncentiveSchemeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class IncentiveSchemeServiceImpl implements IncentiveSchemeService {
    private final IncentiveSchemeRepository incentiveSchemeRepository;
    private final IncentiveSchemeResponseMapper incentiveSchemeResponseMapper;

    @Override
    public Page<IncentiveSchemeResponse> getAllByCriteria(String schemeKey, String schemeName, Pageable pageable) {
        Page<IncentiveScheme> incentiveSchemes = incentiveSchemeRepository.findAllByCriteria(schemeKey, schemeName, pageable);
        List<IncentiveSchemeResponse> incentiveSchemeResponseList = incentiveSchemeResponseMapper.fromEntityToDtoList(incentiveSchemes.getContent());
        return new PageImpl<>(incentiveSchemeResponseList, pageable, incentiveSchemes.getTotalElements());
    }
}
