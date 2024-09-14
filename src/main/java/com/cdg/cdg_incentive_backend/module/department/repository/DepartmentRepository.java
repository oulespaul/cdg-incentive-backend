package com.cdg.cdg_incentive_backend.module.department.repository;

import com.cdg.cdg_incentive_backend.module.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findOneByDepartmentCode(String departmentCode);
}
