package com.cdg.cdg_incentive_backend.module.employee.repository;

import com.cdg.cdg_incentive_backend.module.employee.entity.Employee;
import com.cdg.cdg_incentive_backend.shared.dto.response.FilterResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("""
            SELECT e
            FROM Employee e
            WHERE (:year IS NULL OR EXTRACT(YEAR FROM e.createdAt) = :year)
            AND (:month IS NULL OR EXTRACT(MONTH FROM e.createdAt) = :month)
            AND (:employeeId IS NULL OR e.employeeId LIKE %:employeeId%)
            AND (:branchCode IS NULL OR e.branch LIKE %:branchCode%)
            AND (:branchBU IS NULL OR e.businessUnit LIKE %:branchBU%)
            ORDER BY e.createdAt DESC
            """)
    Page<Employee> findAllResponse(
            String year,
            String month,
            String employeeId,
            String branchCode,
            String branchBU,
            Pageable pageable
    );

    @Query("""
            SELECT DISTINCT EXTRACT(YEAR FROM e.createdAt)
            FROM Employee e
            """)
    List<String> findDistinctYear();

    @Query("""
            SELECT DISTINCT EXTRACT(MONTH FROM e.createdAt)
            FROM Employee e
            """)
    List<String> findDistinctMonth();

    @Query("""
            SELECT DISTINCT new com.cdg.cdg_incentive_backend.shared.dto.response.FilterResponse(
            e.employeeGroup AS label,
            e.employeeGroup AS value
            )
            FROM Employee e
            """)
    List<FilterResponse> findDistinctEmployeeGroup();

    @Query("""
            SELECT DISTINCT new com.cdg.cdg_incentive_backend.shared.dto.response.FilterResponse(
            e.positionDescription AS label,
            e.positionDescription AS value
            )
            FROM Employee e
            """)
    List<FilterResponse> findDistinctPositionDescription();

    @Query("""
            SELECT DISTINCT new com.cdg.cdg_incentive_backend.shared.dto.response.FilterResponse(
            e.corporateTitle AS label,
            e.corporateTitle AS value
            )
            FROM Employee e
            """)
    List<FilterResponse> findDistinctCorporateTitle();
}
