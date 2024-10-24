package com.cdg.cdg_incentive_backend.module.department.entity;

import com.cdg.cdg_incentive_backend.module.brand.entity.Brand;
import com.cdg.cdg_incentive_backend.module.incentivescheme.entity.IncentiveScheme;
import com.cdg.cdg_incentive_backend.module.subdepartment.entity.SubDepartment;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.entity.TargetDSM;
import com.cdg.cdg_incentive_backend.shared.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "department")
public class Department extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "department_code")
    private String departmentCode;
    @Column(name = "department_name")
    private String departmentName;
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<Brand> brands = new HashSet<>();
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<SubDepartment> subDepartments = new HashSet<>();
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<TargetDSM> targetDSMs = new HashSet<>();
    @ManyToMany(mappedBy = "departments", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<IncentiveScheme> incentiveSchemes = new HashSet<>();
}