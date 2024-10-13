package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.entity;

import com.cdg.cdg_incentive_backend.shared.entity.BaseEntity;
import com.cdg.cdg_incentive_backend.module.subdepartment.entity.SubDepartment;
import com.cdg.cdg_incentive_backend.module.targetbranch.entity.TargetBranch;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "target_dept")
public class TargetDept extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "group_dept")
    private String groupDept;
    @Column(name = "goal_dept")
    private BigDecimal goalDept;
    @Column(name = "actual_sales_id_last_year")
    private BigDecimal actualSalesIDLastYear;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_branch_id")
    private TargetBranch targetBranch;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "target_dept_dept_pool",
            joinColumns = @JoinColumn(name = "target_dept_id"),
            inverseJoinColumns = @JoinColumn(name = "sub_dept_id"))
    private Set<SubDepartment> deptPool = new HashSet<>();

    public void addSubDepartment(SubDepartment subDepartment) {
        this.getDeptPool().add(subDepartment);
        subDepartment.getTargetDept().add(this);
    }
}

