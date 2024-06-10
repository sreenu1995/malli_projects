package com.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.employee.dto.EmployeeDto;
import com.employee.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapperInter {

	@Mapping(source = "employeeId", target = "id")
	Employee toEmployee(EmployeeDto employeeDto);

	@Mapping(source = "id", target = "employeeId")
	EmployeeDto toEmployeeDto(Employee employee);
}
