package org.example.controller;

import org.example.dto.CreateEmployeeDTO;
import org.example.dto.mapper.EmployeeDTOMapper;
import org.example.model.Employee;
import org.example.model.Position;
import org.example.repository.PositionRepository;
import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "https://motorphpayroll.netlify.app/")
@RequestMapping ("/employees")
public class RESTEmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionRepository positionRepository;

    @GetMapping("/getEmployees")
    public List<CreateEmployeeDTO> getAllEmployees() throws SQLException {
        return employeeService.getAllEmployees().stream()
                .map(EmployeeDTOMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CreateEmployeeDTO>> searchEmployeeById(@RequestParam(required = false) Integer id) throws SQLException {
        List<Employee> employees;

        if (id != null) {
            Employee employee = employeeService.getEmployeeById(id);
            employees = employee != null ? List.of(employee) : Collections.emptyList();
        } else {
            employees = employeeService.getAllEmployees();
        }

        return ResponseEntity.ok(employees.stream().map(EmployeeDTOMapper::toDTO).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) throws SQLException {
        boolean deleted = employeeService.deleteEmployeeById(id);
        return deleted ? ResponseEntity.ok("Employee deleted successfully") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<CreateEmployeeDTO> createEmployee(@RequestBody CreateEmployeeDTO dto) throws SQLException {
        Employee employee = employeeService.createEmployee(dto);
        CreateEmployeeDTO employeeDto = EmployeeDTOMapper.toDTO(employee);

        return employee.getId() > 0 ? ResponseEntity.ok(employeeDto) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/positions")
    public ResponseEntity<List<Position>> getPositions() {
        List<Position> positions = positionRepository.findAll();
        return ResponseEntity.ok(positions);
    }

    @PostMapping("/updateEmployee")
    public ResponseEntity<CreateEmployeeDTO> updateEmployee(@RequestBody CreateEmployeeDTO dto) throws SQLException {
        Employee employee = employeeService.editEmployee(dto);
        CreateEmployeeDTO empDto = EmployeeDTOMapper.toDTO(employee);
        return ResponseEntity.ok(empDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateEmployeeDTO> getEmployeeById(@PathVariable int id) throws SQLException {
        Employee employee = employeeService.getEmployeeWithAllowanceById(id);
        CreateEmployeeDTO dto = EmployeeDTOMapper.toDTO(employee);

        return dto.getId() > 0 ? ResponseEntity.ok(dto) : ResponseEntity.badRequest().build();
    }
}
