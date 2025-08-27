package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.dto.AllowanceDTO;
import org.example.dto.CreateEmployeeDTO;
import org.example.dto.mapper.EmployeeDTOMapper;
import org.example.model.Allowance;
import org.example.model.Employee;
import org.example.model.Position;
import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("employee");
        model.addAttribute("employee", employee);
        return "profile";
    }

    @GetMapping("/employees")
    public String showEmpManagement(Model model) throws SQLException {
        List<Employee> employees = service.getAllEmployees();
        model.addAttribute("employees", employees);

        return "employees";
    }

    @PostMapping("/employees/create")
    public String createEmployee(@ModelAttribute CreateEmployeeDTO employee, RedirectAttributes redirectAttributes) throws SQLException {
        Employee createdEmployee = service.createEmployee(employee);
        System.out.println(createdEmployee != null ?"Employee created": "Failed to create employee");

        if (createdEmployee != null) {
            redirectAttributes.addFlashAttribute("result", "Employee created successfully");
        } else {
            redirectAttributes.addFlashAttribute("result", "Failed to create employee");
        }

        return "redirect:/employees";
    }

    @PostMapping("/delete")
    public String deleteEmployee(@RequestParam int employeeId, RedirectAttributes redirectAttributes) throws SQLException {
        boolean isDeleted = service.deleteEmployeeById(employeeId);
        System.out.println("Employee deleted result: " + isDeleted);

        return "redirect:/employees";
    }

    @GetMapping("/employee-form")
    public String showEmployeeForm(Model model) {
        List<Position> positions = service.getAllPositions();
        model.addAttribute("positions", positions);

        List<AllowanceDTO> allowances = new ArrayList<>();
        allowances.add(new AllowanceDTO("Rice Subsidy", null));
        allowances.add(new AllowanceDTO("Phone", null));
        allowances.add(new AllowanceDTO("Clothing", null));

        CreateEmployeeDTO dto = new CreateEmployeeDTO();
        dto.setAllowances(allowances);

        model.addAttribute("createEmployeeDTO", dto);

        return "employee-form";
    }

    @GetMapping("/employees/edit/{id}")
    public String view(@PathVariable int id, Model model) throws SQLException {
        List<Position> positions = service.getAllPositions();
        model.addAttribute("positions", positions);

        Employee employee = service.getEmployeeWithAllowanceById(id);

        CreateEmployeeDTO dto = new CreateEmployeeDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setPosition(employee.getPosition().getName());
        dto.setBasicSalary(employee.getSalary().getBasicSalary());
        dto.setGrossSemiMonthly(employee.getSalary().getGrossSemiMonthly());
        dto.setHourlyRate(employee.getSalary().getHourlyRate());
        dto.setSss(employee.getGovernmentId().getSss());
        dto.setPhilhealth(employee.getGovernmentId().getPhilhealth());
        dto.setPagibig(employee.getGovernmentId().getPagibig());
        dto.setTin(employee.getGovernmentId().getTin());

        List<AllowanceDTO> allowancesDTO = new ArrayList<>();
        for (Allowance allowance:  employee.getAllowances()) {
            AllowanceDTO allowanceDTO = new AllowanceDTO();
            allowanceDTO.setTypeName(allowance.getAllowanceType().getName());
            allowanceDTO.setAmount(allowance.getAmount());
            allowancesDTO.add(allowanceDTO);
        }

        dto.setAllowances(allowancesDTO);

        model.addAttribute("createEmployeeDTO", dto);
        return "employee-form";
    }

    @PostMapping("/employees/update")
    public String update(@ModelAttribute CreateEmployeeDTO dto) throws SQLException {
        Employee employee = service.editEmployee(dto);

        return "redirect:/employees";
    }

    @GetMapping("/api/employees/search")
    public ResponseEntity<List<CreateEmployeeDTO>> searchEmployee(@RequestParam(value = "empId", required = false ) Integer id) throws SQLException {
        List<Employee> employees;

        if (id != null) {
            Employee employee = service.getEmployeeById(id);
            employees = employee != null ? List.of(employee) : Collections.emptyList();
        } else {
            employees = service.getAllEmployees();
        }

        return ResponseEntity.ok(employees.stream().map(EmployeeDTOMapper::toDTO).collect(Collectors.toList()));
    }
}
