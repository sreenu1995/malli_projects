package com.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dto.EmployeeDto;
import com.employee.mapper.EmployeeMapperInter;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeMapperInter employeeMapper;

	public List<EmployeeDto> saveEmployee(List<EmployeeDto> employeeDtos) {
		
		System.out.println("size of employees"+employeeDtos.size());
		
		List<Employee> employees=new ArrayList<>();
		for(EmployeeDto employeeDto:employeeDtos)
			employees.add(employeeRepository.save(employeeMapper.toEmployee(employeeDto)));
		
		for(Employee emp:employees) {
			employeeDtos.add(employeeMapper.toEmployeeDto(emp));
		}
		
		
		
		return employeeDtos;
	}
}
