package com.employee.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeDto {

	public int employeeId;
	public String firstName;
	public String lastName;
	public String mobileNumber;
	public String email;
	public Date hireDate;
	public String jobId;
	public Double salary;
	public String managerId;
	public int departmentId;
}