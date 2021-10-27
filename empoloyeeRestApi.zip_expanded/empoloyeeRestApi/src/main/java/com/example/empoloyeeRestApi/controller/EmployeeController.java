package com.example.empoloyeeRestApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.empoloyeeRestApi.exception.ResourceNotFoundException;
import com.example.empoloyeeRestApi.model.Employee;
import com.example.empoloyeeRestApi.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//get all employees
	@GetMapping("employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	//get a specific employee
		@GetMapping("/employees/{id}")
		public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
				throws ResourceNotFoundException {
			Employee employee = employeeRepository.findById(employeeId)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
			return ResponseEntity.ok().body(employee);
		}
		
	
}
