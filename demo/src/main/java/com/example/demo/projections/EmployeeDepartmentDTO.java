package com.example.demo.projections;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface EmployeeDepartmentDTO {
    Integer getEmployeeId();

    LocalDate getRegistrationDate();

    String getEmployeeName();

    String getEmail();

    String getPosition();

    String getPhoneNo();

    Integer getDepartmentId();

    String getName();

    String getLocation();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    Integer getProjectId();

    String getProjectName();

    Date getStartDate();

    Date getEndDate();

    BigDecimal getBudget();

    Integer getAverage();

    String getDepartmentName();

    Integer getEmployeeCount();


}
