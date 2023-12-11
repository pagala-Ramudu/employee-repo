package org.ci.demo.serviceImpl;



import java.util.List;
import java.util.stream.Collectors;

import org.ci.demo.dto.EmployeeRequestDTO;
import org.ci.demo.dto.EmployeeResponseDTO;
import org.ci.demo.entity.Employee;
import org.ci.demo.exception.ResourceNotFoundException;
import org.ci.demo.repository.EmployeeRepository;
import org.ci.demo.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private  EmployeeRepository employeeRepository;

	//method to save employee in DB
	@Override
	public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(requestDTO, employee);
		Employee savedEmployee = employeeRepository.save(employee);
		EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
		BeanUtils.copyProperties(savedEmployee, responseDTO);
		return responseDTO;
	}
	
	//get employee by id method
	@Override
	public EmployeeResponseDTO getEmployeeById(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
		BeanUtils.copyProperties(employee, responseDTO);
		return responseDTO;
	}

	//method to get list of employees
	@Override
	public List<EmployeeResponseDTO> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream()
				.map(employee -> {
					EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
					BeanUtils.copyProperties(employee, responseDTO);
					return responseDTO;
				})
				.collect(Collectors.toList());
	}
	
	//method to implement  pagination
	@Override
	public List<EmployeeResponseDTO> getAllEmployees(int page, int size) {
		List<Employee> employees = employeeRepository.findAll(PageRequest.of(page, size)).getContent();
		return employees.stream()
				.map(employee -> {
					EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
					BeanUtils.copyProperties(employee, responseDTO);
					return responseDTO;
				})
				.collect(Collectors.toList());
	}
	
	//method to implement sorting and pagination
	@Override
	public List<EmployeeResponseDTO> getAllEmployeespaginationandSorting(int page, int size, String field) {
		List<Employee> employees = employeeRepository.findAll(PageRequest.of(page, size).withSort(Sort.by(field))).getContent();
		return employees.stream()
				.map(employee -> {
					EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
					BeanUtils.copyProperties(employee, responseDTO);
					return responseDTO;
				})
				.collect(Collectors.toList());
	}
	
	
	//method to update employee from db
	@Override
	public EmployeeResponseDTO updateEmployee(long id, EmployeeRequestDTO requestDTO) {
		Employee employee = employeeRepository.findById(id).get();

		employee.setFirstName(requestDTO.getFirstName());
		employee.setLastName(requestDTO.getLastName());
		employee.setAge(requestDTO.getAge());
		employee.setDepartment(requestDTO.getDepartment());
		employee.setSalary(requestDTO.getSalary());
		
		BeanUtils.copyProperties(employee, requestDTO);
		Employee savedEmployee = employeeRepository.save(employee);
		
		EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
		BeanUtils.copyProperties(savedEmployee, responseDTO);
		return responseDTO;
		

	}
	


	


}
