package org.ci.demo.service;



import java.util.List;

import org.ci.demo.dto.EmployeeRequestDTO;
import org.ci.demo.dto.EmployeeResponseDTO;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;

public interface EmployeeService {
	
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO);

    EmployeeResponseDTO getEmployeeById(Long id);

    List<EmployeeResponseDTO> getAllEmployees();

    List<EmployeeResponseDTO> getAllEmployees(int page, int size);
    
    List<EmployeeResponseDTO> getAllEmployeespaginationandSorting(int page, int size, String field);

    public EmployeeResponseDTO updateEmployee(long id, EmployeeRequestDTO requestDTO);

}
