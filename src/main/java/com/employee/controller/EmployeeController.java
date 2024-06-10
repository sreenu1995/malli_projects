package com.employee.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.employee.dto.EmployeeDto;
import com.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class EmployeeController {

	public static final String BASE_URL = "/v1/employee";

	static String[] HEADERs = { "EMPLOYEE_ID", "FIRST_NAME", "LAST_NAME", "EMAIL","PHONE_NUMBER","HIRE_DATE","JOB_ID","SALARY","MANAGER_ID","DEPARTMENT_ID" };
	@Autowired
	private EmployeeService employeeService;

	@PostMapping(path = BASE_URL + "/save")
	public ResponseEntity<List<EmployeeDto>> saveEmployee(@RequestBody MultipartFile multipartFile) throws IOException, ParseException {
		log.info("Received the Post request to store the employee data");

		System.out.println("Received a Request from postman");
		List<EmployeeDto> employeeDtos=csvToEmployeeDto(multipartFile);
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(employeeDtos));
	}
	
	public List<EmployeeDto> csvToEmployeeDto(MultipartFile multipartFile) throws IOException, ParseException {
		
		
		List<EmployeeDto> list=new ArrayList<>();
		
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
		CSVParser csvParser=new CSVParser(bufferedReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase());
		
		List<CSVRecord> csvRecords=csvParser.getRecords();
		
		for(CSVRecord csvRecord:csvRecords) {
			EmployeeDto employeeDto=new EmployeeDto();
			employeeDto.setEmployeeId(Integer.parseInt(csvRecord.get("EMPLOYEE_ID")));
			employeeDto.setFirstName(csvRecord.get("FIRST_NAME"));
			employeeDto.setLastName(csvRecord.get("LAST_NAME"));
			employeeDto.setEmail(csvRecord.get("EMAIL"));
			employeeDto.setMobileNumber(csvRecord.get("PHONE_NUMBER"));
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
			dateFormat.setTimeZone(TimeZone.getDefault());
			Date date=new Date(dateFormat.parse(csvRecord.get("HIRE_DATE")).getTime());
			employeeDto.setHireDate(date);
			employeeDto.setJobId(csvRecord.get("JOB_ID"));
			employeeDto.setSalary(Double.parseDouble(csvRecord.get("SALARY")));
			employeeDto.setManagerId(csvRecord.get("MANAGER_ID"));
			employeeDto.setDepartmentId(Integer.parseInt(csvRecord.get("DEPARTMENT_ID")));
			list.add(employeeDto);
		}
		
		return list;
	}

}
