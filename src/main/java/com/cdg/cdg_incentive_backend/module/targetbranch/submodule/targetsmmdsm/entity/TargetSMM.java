package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.entity;

import com.cdg.cdg_incentive_backend.shared.entity.BaseEntity;
import com.cdg.cdg_incentive_backend.module.targetbranch.entity.TargetBranch;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "target_smm")
public class TargetSMM extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "smm_id")
    private String smmId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_branch_id")
    private TargetBranch targetBranch;
    @OneToMany(mappedBy = "targetSMM", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<TargetDSM> targetDSMs = new HashSet<>();
}

