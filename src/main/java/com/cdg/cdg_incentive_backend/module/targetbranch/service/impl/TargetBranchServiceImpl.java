package com.cdg.cdg_incentive_backend.module.targetbranch.service.impl;

import com.cdg.cdg_incentive_backend.module.brand.entity.Brand;
import com.cdg.cdg_incentive_backend.module.brand.service.BrandService;
import com.cdg.cdg_incentive_backend.module.department.entity.Department;
import com.cdg.cdg_incentive_backend.module.department.service.DepartmentService;
import com.cdg.cdg_incentive_backend.module.subdepartment.entity.SubDepartment;
import com.cdg.cdg_incentive_backend.module.subdepartment.service.SubDepartmentService;
import com.cdg.cdg_incentive_backend.module.targetbranch.dto.request.CreateTargetBranchRequest;
import com.cdg.cdg_incentive_backend.module.targetbranch.dto.response.TargetBranchDetailResponse;
import com.cdg.cdg_incentive_backend.module.targetbranch.dto.response.TargetBranchResponse;
import com.cdg.cdg_incentive_backend.module.targetbranch.entity.TargetBranch;
import com.cdg.cdg_incentive_backend.module.targetbranch.mapper.TargetBranchDetailResponseMapper;
import com.cdg.cdg_incentive_backend.module.targetbranch.mapper.TargetBranchResponseMapper;
import com.cdg.cdg_incentive_backend.module.targetbranch.repository.TargetBranchRepository;
import com.cdg.cdg_incentive_backend.module.targetbranch.service.TargetBranchService;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.dto.request.TargetDeptRequest;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.entity.TargetDept;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.service.TargetDeptService;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdmm.dto.request.TargetDMMRequest;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdmm.entity.TargetDMM;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdmm.service.TargetDMMService;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.dto.request.TargetInHouseRequest;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.entity.TargetInHouse;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.service.TargetInHouseService;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.dto.request.TargetDSMRequest;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.dto.request.TargetSMMDSMRequest;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.entity.TargetDSM;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.entity.TargetSMM;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.service.TargetDSMService;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.service.TargetSMMService;
import com.cdg.cdg_incentive_backend.module.targetcommission.entity.TargetCommission;
import com.cdg.cdg_incentive_backend.module.targetcommission.service.TargetCommissionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TargetBranchServiceImpl implements TargetBranchService {
    private final TargetBranchRepository targetBranchRepository;
    private final TargetCommissionService targetCommissionService;
    private final TargetInHouseService targetInHouseService;
    private final BrandService brandService;
    private final TargetBranchResponseMapper targetBranchResponseMapper;
    private final TargetBranchDetailResponseMapper targetBranchDetailResponseMapper;
    private final TargetDeptService targetDeptService;
    private final SubDepartmentService subDepartmentService;
    private final TargetSMMService targetSMMService;
    private final DepartmentService departmentService;
    private final TargetDSMService targetDSMService;
    private final TargetDMMService targetDMMService;

    @Transactional
    @Override
    public void createTargetBranch(CreateTargetBranchRequest request) {
        TargetCommission targetCommission = targetCommissionService.getOneById(request.getTargetCommissionId());

        // Find for check existing to re-use entity
        TargetBranch targetBranch = targetBranchRepository.findByTargetCommissionId(targetCommission.getId())
                .orElseGet(() -> TargetBranch.builder()
                        .status("New")
                        .requestedAt(LocalDateTime.now())
                        .targetCommission(targetCommission)
                        .build());

        targetBranch = targetBranchRepository.save(targetBranch);

        // Clear target in-house by target branch for upsert new target in-house list
        targetInHouseService.deleteByTargetBranchId(targetBranch.getId());
        for (TargetInHouseRequest targetInHouseRequest : request.getTargetInHouseList()) {
            Brand brand = brandService.getById(targetInHouseRequest.getBrandId());
            TargetInHouse targetInHouse = TargetInHouse.builder()
                    .targetBranch(targetBranch)
                    .brand(brand)
                    .groupBrand(targetInHouseRequest.getGroupBrand())
                    .goalBrand(targetInHouseRequest.getGoalBrand())
                    .actualSalesIDLastYear(targetInHouseRequest.getActualSalesIDLastYear())
                    .build();

            targetInHouseService.save(targetInHouse);
        }

        // Clear target in-dept by target branch for upsert new target in-dept list
        targetDeptService.deleteByTargetBranchId(targetBranch.getId());
        for (TargetDeptRequest targetDeptRequest : request.getTargetDeptList()) {
            TargetDept targetDept = TargetDept.builder()
                    .targetBranch(targetBranch)
                    .groupDept(targetDeptRequest.getGroupDept())
                    .goalDept(targetDeptRequest.getGoalDept())
                    .actualSalesIDLastYear(targetDeptRequest.getActualSalesIDLastYear())
                    .deptPool(new HashSet<>())
                    .build();

            for (Integer subDepartmentId : targetDeptRequest.getSubDepartmentPool()) {
                SubDepartment subDepartment = subDepartmentService.getOneById(subDepartmentId);
                targetDept.addSubDepartment(subDepartment);
            }

            targetDeptService.save(targetDept);
        }

        // Clear target smm-dsm by target branch for upsert new target smm-dsm list
        targetSMMService.deleteByTargetBranchId(targetBranch.getId());
        for (TargetSMMDSMRequest targetSMMDSMRequest : request.getTargetSMMDSMList()) {
            TargetSMM targetSMM = TargetSMM.builder()
                    .smmId(targetSMMDSMRequest.getSmmId())
                    .targetBranch(targetBranch)
                    .build();
            TargetSMM targetSMMEntitySaved = targetSMMService.save(targetSMM);
            for (TargetDSMRequest targetDSMRequest : targetSMMDSMRequest.getTargetDSMList()) {
                Department department = departmentService.getById(targetDSMRequest.getDepartmentId());
                SubDepartment subDepartment = subDepartmentService.getOneById(targetDSMRequest.getSubDepartmentId());
                TargetDSM targetDSM = TargetDSM.builder()
                        .dsmId(targetDSMRequest.getDsmId())
                        .targetSMM(targetSMMEntitySaved)
                        .department(department)
                        .subDepartment(subDepartment)
                        .goalDept(targetDSMRequest.getGoalDept())
                        .actualSalesLastYear(targetDSMRequest.getActualSalesLastYear())
                        .goalId(targetDSMRequest.getGoalId())
                        .actualSalesIDLastYear(targetDSMRequest.getActualSalesIDLastYear())
                        .build();
                targetDSMService.save(targetDSM);
            }
        }

        // Clear target dmm by target branch for upsert new target dmm list
        targetDMMService.deleteByTargetBranchId(targetBranch.getId());
        for (TargetDMMRequest targetDMMRequest : request.getTargetDMMList()) {
            Department department = departmentService.getById(targetDMMRequest.getDepartmentId());
            SubDepartment subDepartment = subDepartmentService.getOneById(targetDMMRequest.getSubDepartmentId());
            TargetDMM targetDMM = TargetDMM.builder()
                    .dmmId(targetDMMRequest.getDmmId())
                    .targetBranch(targetBranch)
                    .department(department)
                    .subDepartment(subDepartment)
                    .goalDept(targetDMMRequest.getGoalDept())
                    .actualSalesLastYear(targetDMMRequest.getActualSalesLastYear())
                    .goalId(targetDMMRequest.getGoalId())
                    .actualSalesIDLastYear(targetDMMRequest.getActualSalesIDLastYear())
                    .build();
            targetDMMService.save(targetDMM);
        }
    }

    @Override
    public TargetBranchResponse getOneByTargetCommissionId(Integer targetCommissionId) {
        Optional<TargetBranch> targetBranchOptional = targetBranchRepository.findByTargetCommissionId(targetCommissionId);
        return targetBranchOptional.map(targetBranchResponseMapper::fromEntityToDto).orElse(null);
    }

    @Override
    public Page<TargetBranchDetailResponse> getAllDetail(String year, String month, Pageable pageable) {
        Page<TargetBranch> targetBranchPage = targetBranchRepository.findAllDetail(year, month, pageable);
        List<TargetBranchDetailResponse> targetBranchDetailResponseList = targetBranchDetailResponseMapper.fromEntityToDtoList(
                targetBranchPage.getContent()
        );
        return new PageImpl<>(targetBranchDetailResponseList, pageable, targetBranchPage.getTotalElements());
    }
}
