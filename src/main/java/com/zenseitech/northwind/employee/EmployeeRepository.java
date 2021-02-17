package com.zenseitech.northwind.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {
    @Query("Select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
    Employee findByFirstAndLastName(String firstName, String lastName);
}
