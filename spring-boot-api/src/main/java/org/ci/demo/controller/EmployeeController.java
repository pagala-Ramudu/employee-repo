package org.ci.demo.controller;


import org.ci.demo.dto.EmployeeRequestDTO;
import org.ci.demo.dto.EmployeeResponseDTO;
import org.ci.demo.serviceImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private  EmployeeServiceImpl employeeService;


	//method to create employee
	@PostMapping("/save")
	public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO requestDTO) {
		//calling method from employee service
		EmployeeResponseDTO responseDTO = employeeService.createEmployee(requestDTO);
		//return employeeResponse and capture the status code
		return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
	}


	//method to get employee by id
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
		EmployeeResponseDTO responseDTO = employeeService.getEmployeeById(id);
		//return employeeResponse and capture the status code
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}


	//method to get all employees
	@GetMapping("/getAll")
	public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
		List<EmployeeResponseDTO> responseDTOList = employeeService.getAllEmployees();
		//return employeeResponse and capture the status code
		return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
	}


	//method to get employees based on paging
	@GetMapping("/paged")
	public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployeesPaged(
			@RequestParam int page,
			@RequestParam int size) {
		List<EmployeeResponseDTO> responseDTOList = employeeService.getAllEmployees(page, size);
		//return employeeResponse and capture the status code
		return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
	}


	
	//method to get employees by paging and sorting order
	@GetMapping("/pagingAndSorting")
	public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployeesPaginationandsorting(
			@RequestParam int page,
			@RequestParam int size,
			@RequestParam String field) {
		List<EmployeeResponseDTO> responseDTOList = employeeService.getAllEmployeespaginationandSorting(page, size, field);
		//return employeeResponse and capture the status code
		return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
	}


	//method to update employee
	@PutMapping("/update/{id}")
	public ResponseEntity<EmployeeResponseDTO>updateEmployee(
			@PathVariable long id, @RequestBody EmployeeRequestDTO requestDTO) {
		EmployeeResponseDTO responseDTOList = employeeService.updateEmployee(id, requestDTO);
		//return employeeResponse and capture the status code
		return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
	}





}
