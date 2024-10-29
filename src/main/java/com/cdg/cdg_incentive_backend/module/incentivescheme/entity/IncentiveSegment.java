package com.cdg.cdg_incentive_backend.module.incentivescheme.entity;

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
@Table(name = "incentive_segment")
public class IncentiveSegment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "is_active")
    private Boolean isActive;
    @ManyToMany(mappedBy = "incentiveSegments", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<IncentiveScheme> incentiveSchemes = new HashSet<>();
}
