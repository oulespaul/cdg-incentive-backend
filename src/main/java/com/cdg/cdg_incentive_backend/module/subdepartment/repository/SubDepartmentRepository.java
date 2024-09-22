package com.cdg.cdg_incentive_backend.module.subdepartment.repository;

import com.cdg.cdg_incentive_backend.module.subdepartment.entity.SubDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubDepartmentRepository extends JpaRepository<SubDepartment, Integer> {
    @Query("""
            SELECT sd
            FROM SubDepartment sd
            WHERE (:departmentId IS NULL OR sd.department.id = :departmentId)
            """)
    List<SubDepartment> getAll(Integer departmentId);
}
