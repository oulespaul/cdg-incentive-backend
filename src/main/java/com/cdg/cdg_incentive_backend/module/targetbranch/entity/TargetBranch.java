package com.cdg.cdg_incentive_backend.module.targetbranch.entity;

import com.cdg.cdg_incentive_backend.entity.BaseEntity;
import com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.entity.TargetInHouse;
import com.cdg.cdg_incentive_backend.module.targetcommission.entity.TargetCommission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "target_branch")
@EqualsAndHashCode(callSuper = true)
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
    @OneToMany(mappedBy = "targetBranch")
    private Set<TargetInHouse> targetInHouses = new HashSet<>();
}