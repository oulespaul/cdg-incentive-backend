package com.cdg.cdg_incentive_backend.module.targetbranch.service.impl;

import com.cdg.cdg_incentive_backend.module.brand.entity.Brand;
import com.cdg.cdg_incentive_backend.module.brand.service.BrandService;
import com.cdg.cdg_incentive_backend.module.targetbranch.TargetBranchRepository;
import com.cdg.cdg_incentive_backend.module.targetbranch.dto.request.CreateTargetBranchRequest;
import com.cdg.cdg_incentive_backend.module.targetbranch.entity.TargetBranch;
import com.cdg.cdg_incentive_backend.module.targetbranch.service.TargetBranchService;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.dto.request.TargetInHouseRequest;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.entity.TargetInHouse;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.service.TargetInHouseService;
import com.cdg.cdg_incentive_backend.module.targetcommission.entity.TargetCommission;
import com.cdg.cdg_incentive_backend.module.targetcommission.service.TargetCommissionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class TargetBranchServiceImpl implements TargetBranchService {
    private final TargetBranchRepository targetBranchRepository;
    private final TargetCommissionService targetCommissionService;
    private final TargetInHouseService targetInHouseService;
    private final BrandService brandService;

    @Transactional
    @Override
    public void createTargetBranch(CreateTargetBranchRequest request) {
        TargetCommission targetCommission = targetCommissionService.getOneById(request.getTargetCommissionId());

        TargetBranch targetBranch = TargetBranch.builder()
                .status("NEW")
                .requestedAt(LocalDateTime.now())
                .targetCommission(targetCommission)
                .build();

        targetBranch = targetBranchRepository.save(targetBranch);

        for (TargetInHouseRequest targetInHouseRequest : request.getTargetInHouseList()) {
            Brand brand = brandService.getById(targetInHouseRequest.getBrandId());
            TargetInHouse targetInHouse = TargetInHouse.builder()
                    .targetBranch(targetBranch)
                    .brand(brand)
                    .groupBrand(targetInHouseRequest.getGroupBrand())
                    .goalBrand(targetInHouseRequest.getGoalBrand())
                    .build();

            targetInHouseService.save(targetInHouse);
        }
    }
}
