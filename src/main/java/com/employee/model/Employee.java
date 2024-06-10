package com.employee.model;

import java.sql.Date;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Employee {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	@Id
	public Long id;

	@Column(name = "employee_id")
	public int employeeId;
	
	@Column(name = "first_name")
	public String firstName;

	@Column(name = "last_name")
	public String lastName;

	@Column(name = "mobile_number")
	public String mobileNumber;

	@Column
	public String email;

	@Column(name = "hire_date")
	public Date hireDate;

	@Column(name = "job_id")
	public String jobId;

	public Double salary;

	@Column(name = "manager_id")
	public String managerId;

	@Column(name = "department_id")
	public String departmentId;

	@Column(name = "record_created_at")
	@CreationTimestamp
	protected Instant createdAt;

	@Column(name = "record_updated_at")
	@UpdateTimestamp
	protected Instant updatedAt;
}
