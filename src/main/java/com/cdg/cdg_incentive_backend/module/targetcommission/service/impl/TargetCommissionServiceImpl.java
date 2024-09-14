package com.cdg.cdg_incentive_backend.module.targetcommission.service.impl;

import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionFilterResponse;
import com.cdg.cdg_incentive_backend.module.targetcommission.entity.TargetCommission;
import com.cdg.cdg_incentive_backend.module.targetcommission.mapper.TargetCommissionFilterMapper;
import com.cdg.cdg_incentive_backend.module.targetcommission.repositories.TargetCommissionRepository;
import com.cdg.cdg_incentive_backend.module.targetcommission.service.TargetCommissionService;
import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionResponse;
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
    public void saveAll(List<TargetCommission> targetCommissionList) {
        targetCommissionRepository.saveAll(targetCommissionList);
    }

    @Override
    public List<TargetCommissionFilterResponse> getDistinctYear() {
        List<String> distinctYearList = targetCommissionRepository.findDistinctYear();
        return targetCommissionFilterMapper.stringValueToDTOList(distinctYearList);
    }

    @Override
    public List<TargetCommissionFilterResponse> getDistinctMonth() {
        List<String> distinctMonthList = targetCommissionRepository.findDistinctMonth();
        return targetCommissionFilterMapper.stringValueToDTOList(distinctMonthList);
    }

    @Override
    public List<TargetCommissionFilterResponse> getDistinctBranchNumber() {
        return targetCommissionRepository.findDistinctBranchNumber();
    }
}
