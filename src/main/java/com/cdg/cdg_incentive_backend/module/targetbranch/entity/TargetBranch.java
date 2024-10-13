package com.cdg.cdg_incentive_backend.module.targetbranch.entity;

import com.cdg.cdg_incentive_backend.shared.entity.BaseEntity;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdept.entity.TargetDept;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetdmm.entity.TargetDMM;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.entity.TargetInHouse;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.entity.TargetSMM;
import com.cdg.cdg_incentive_backend.module.targetcommission.entity.TargetCommission;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "target_branch")
public class TargetBranch extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "status")
    private String status;
    @Column(name = "requested_at")
    private LocalDateTime requestedAt;
    @Column(name = "requested_by")
    private String requestedBy;
    @Column(name = "approved_at")
    private LocalDateTime approvedAt;
    @Column(name = "approved_by")
    private String approvedBy;
    @Column(name = "rejected_at")
    private LocalDateTime rejectedAt;
    @Column(name = "rejected_by")
    private String rejectedBy;
    @Column(name = "rejected_reason")
    private String rejectedReason;
    @Column(name = "calculated_at")
    private LocalDateTime calculatedAt;
    @Column(name = "calculated_by")
    private String calculatedBy;
    @OneToOne
    @JoinColumn(name = "target_commission_id", referencedColumnName = "id")
    private TargetCommission targetCommission;
    @OneToMany(mappedBy = "targetBranch", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<TargetInHouse> targetInHouses = new HashSet<>();
    @OneToMany(mappedBy = "targetBranch", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<TargetDept> targetDept = new HashSet<>();
    @OneToMany(mappedBy = "targetBranch", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<TargetSMM> targetSMMs = new HashSet<>();
    @OneToMany(mappedBy = "targetBranch", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<TargetDMM> targetDMMs = new HashSet<>();
}