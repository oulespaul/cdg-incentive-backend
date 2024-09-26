package com.cdg.cdg_incentive_backend.module.subdepartment.entity;

import com.cdg.cdg_incentive_backend.entity.BaseEntity;
import com.cdg.cdg_incentive_backend.module.brand.entity.Brand;
import com.cdg.cdg_incentive_backend.module.department.entity.Department;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.entity.TargetDept;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.entity.TargetDSM;
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
@Table(name = "sub_department")
public class SubDepartment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sub_department_code")
    private String subDepartmentCode;
    @Column(name = "sub_department_name")
    private String subDepartmentName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    @OneToMany(mappedBy = "subDepartment")
    @JsonIgnore
    private Set<Brand> brands = new HashSet<>();
    @ManyToMany(mappedBy = "deptPool", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<TargetDept> targetDept = new HashSet<>();
    @OneToMany(mappedBy = "subDepartment")
    @JsonIgnore
    private Set<TargetDSM> targetDSMs = new HashSet<>();
}