package org.example.dto.mapper;

import org.example.dto.AllowanceDTO;
import org.example.dto.CreateEmployeeDTO;
import org.example.model.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDTOMapper {
    public static CreateEmployeeDTO toDTO(Employee employee) {
        CreateEmployeeDTO dto = new CreateEmployeeDTO();

        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());

        if (employee.getPosition() != null) {
            dto.setPosition(employee.getPosition().getName());
        }

        if (employee.getSalary() != null) {
            dto.setBasicSalary(employee.getSalary().getBasicSalary());
            dto.setGrossSemiMonthly(employee.getSalary().getGrossSemiMonthly());
            dto.setHourlyRate(employee.getSalary().getHourlyRate());
        }

        if (employee.getGovernmentId() != null) {
            dto.setSss(employee.getGovernmentId().getSss());
            dto.setPhilhealth(employee.getGovernmentId().getPhilhealth());
            dto.setPagibig(employee.getGovernmentId().getPagibig());
            dto.setTin(employee.getGovernmentId().getTin());
        }

        List<AllowanceDTO> allowanceDTOs = employee.getAllowances().stream()
                .map(a -> new AllowanceDTO(a.getAllowanceType().getName(), a.getAmount()))
                .collect(Collectors.toList());

        dto.setAllowances(allowanceDTOs);

        return dto;
    }
}
