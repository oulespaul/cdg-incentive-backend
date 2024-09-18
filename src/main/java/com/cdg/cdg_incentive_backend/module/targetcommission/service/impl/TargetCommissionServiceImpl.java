package com.cdg.cdg_incentive_backend.module.targetcommission.service.impl;

import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionFilterResponse;
import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionResponse;
import com.cdg.cdg_incentive_backend.module.targetcommission.entity.TargetCommission;
import com.cdg.cdg_incentive_backend.module.targetcommission.mapper.TargetCommissionFilterMapper;
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
    private final TargetCommissionFilterMapper targetCommissionFilterMapper;
    private final TargetCommissionResponseMapper targetCommissionResponseMapper;

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
    public List<TargetCommissionFilterResponse> getDistinctYearByBranchId(Integer branchId) {
        List<String> distinctYearList = targetCommissionRepository.findDistinctYearByBranchId(branchId);
        return targetCommissionFilterMapper.stringValueToDTOList(distinctYearList);
    }

    @Override
    public List<TargetCommissionFilterResponse> getDistinctMonthByBranchId(Integer branchId) {
        List<String> distinctMonthList = targetCommissionRepository.findDistinctMonthByBranchId(branchId);
        return targetCommissionFilterMapper.stringValueToDTOList(distinctMonthList);
    }

    @Override
    public List<TargetCommissionFilterResponse> getDistinctBranchNumber() {
        return targetCommissionRepository.findDistinctBranchNumber();
    }

    @Override
    public TargetCommissionResponse getOneByYearAndMonthAndBranchId(String year, String month, Integer branchId) {
        TargetCommission targetCommission = targetCommissionRepository.findOneByYearAndMonthAndBranchId(year, month, branchId).orElse(null);
        return targetCommissionResponseMapper.fromEntityToDTO(targetCommission);
    }
}
