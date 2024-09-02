package com.cdg.cdg_incentive_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<AppUser> appUsers = new HashSet<>();
}
