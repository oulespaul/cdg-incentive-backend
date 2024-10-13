package com.cdg.cdg_incentive_backend.module.targetcommission.service.impl;

import com.cdg.cdg_incentive_backend.shared.dto.response.FilterResponse;
import com.cdg.cdg_incentive_backend.shared.mapper.FilterMapper;
import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionResponse;
import com.cdg.cdg_incentive_backend.module.targetcommission.entity.TargetCommission;
import com.cdg.cdg_incentive_backend.module.targetcommission.mapper.TargetCommissionResponseMapper;
import com.cdg.cdg_incentive_backend.module.targetcommission.repository.TargetCommissionRepository;
import com.cdg.cdg_incentive_backend.module.targetcommission.service.TargetCommissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TargetCommissionServiceImpl implements TargetCommissionService {
    private final TargetCommissionRepository targetCommissionRepository;
    private final TargetCommissionResponseMapper targetCommissionResponseMapper;
    private final FilterMapper filterMapper;

    @Override
    public Page<TargetCommissionResponse> getAllResponse(
            String year,
            String month,
            String branchNumber,
            String branchBU,
            String branchCode,
            Integer page,
            Integer pageSize
    ) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return targetCommissionRepository.findAllResponse(year,
                month,
                branchNumber,
                branchBU,
                branchCode,
                pageRequest);
    }

    @Override
    public TargetCommission getOneById(Integer targetCommissionID) {
        return targetCommissionRepository.findById(targetCommissionID)
                .orElseThrow(() -> new RuntimeException("TargetCommission not found"));
    }

    @Override
    public void saveAll(List<TargetCommission> targetCommissionList) {
        targetCommissionRepository.saveAll(targetCommissionList);
    }

    @Override
    public List<FilterResponse> getDistinctYearByBranchId(Integer branchId) {
        List<String> distinctYearList = targetCommissionRepository.findDistinctYearByBranchId(branchId);
        return filterMapper.stringValueToDTOList(distinctYearList);
    }

    @Override
    public List<FilterResponse> getDistinctMonthByBranchId(Integer branchId) {
        List<String> distinctMonthList = targetCommissionRepository.findDistinctMonthByBranchId(branchId);
        return filterMapper.stringValueToDTOList(distinctMonthList);
    }

    @Override
    public List<FilterResponse> getDistinctBranchNumber() {
        return targetCommissionRepository.findDistinctBranchNumber();
    }

    @Override
    public TargetCommissionResponse getOneByYearAndMonthAndBranchId(String year, String month, Integer branchId) {
        TargetCommission targetCommission = targetCommissionRepository.findOneByYearAndMonthAndBranchId(year, month, branchId).orElse(null);
        return targetCommissionResponseMapper.fromEntityToDTO(targetCommission);
    }
}
