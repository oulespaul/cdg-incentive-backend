package com.cdg.cdg_incentive_backend.module.authentication.entity;

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
@Table(name = "role")
public class Role extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "role_name")
    private String roleName;
    private String description;
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Set<AppUser> appUsers = new HashSet<>();
}
