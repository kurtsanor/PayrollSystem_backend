package org.example.service;

import org.example.dto.AllowanceDTO;
import org.example.model.*;
import org.example.repository.AllowanceRepository;
import org.example.repository.AllowanceTypeRepository;
import org.example.repository.EmployeeRepository;
import org.example.dto.CreateEmployeeDTO;
import org.example.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private AllowanceTypeRepository allowanceTypeRepository;

    @Autowired
    private AllowanceRepository allowanceRepository;


    public Employee getEmployeeById(int employeeId) throws SQLException {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public Employee getEmployeeWithAllowanceById(int employeeId) throws SQLException {
        return employeeRepository.findByIdWithAllowances(employeeId).orElse(null);
    }

    public List<Employee> getAllEmployees() throws SQLException {
        return employeeRepository.findAll();
    }

    public Employee editEmployee(CreateEmployeeDTO dto) throws SQLException {
        Employee employee = employeeRepository.findById(dto.getId()).orElseThrow();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());

        Position position = positionRepository.findByName(dto.getPosition()).orElse(null);
        employee.setPosition(position);

        Salary salary = employee.getSalary();
        salary.setBasicSalary(dto.getBasicSalary());
        salary.setGrossSemiMonthly(dto.getGrossSemiMonthly());
        salary.setHourlyRate(dto.getHourlyRate());

        employee.setSalary(salary);

        GovernmentId governmentId = employee.getGovernmentId();
        governmentId.setSss(dto.getSss());
        governmentId.setPhilhealth(dto.getPhilhealth());
        governmentId.setPagibig(dto.getPagibig());
        governmentId.setTin(dto.getTin());

        employee.setGovernmentId(governmentId);



        List<Allowance> allowances = employee.getAllowances();
        allowances.clear();

        for (AllowanceDTO allowanceDTO: dto.getAllowances()) {
            Allowance allowance = new Allowance();
            System.out.println("Type = " +allowanceDTO.getTypeName());
            allowance.setAllowanceType(allowanceTypeRepository.findByName(allowanceDTO.getTypeName()).orElseThrow(()-> new IllegalArgumentException("Allowance type not found")));
            allowance.setAmount(allowanceDTO.getAmount());
            allowance.setEmployee(employee);
            allowances.add(allowance);
        }

        employee.setAllowances(allowances);

        return employeeRepository.save(employee);
    }

    public Employee createEmployee(CreateEmployeeDTO employeeDTO) throws SQLException {
        Optional<Position> position = positionRepository.findByName(employeeDTO.getPosition());

        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setPosition(position.orElseThrow(() -> new IllegalArgumentException("Position not found")));

        Salary salary = new Salary();
        salary.setEmployee(employee);
        salary.setBasicSalary(employeeDTO.getBasicSalary());
        salary.setGrossSemiMonthly(employeeDTO.getGrossSemiMonthly());
        salary.setHourlyRate(employeeDTO.getHourlyRate());

        GovernmentId governmentId = new GovernmentId();
        governmentId.setEmployee(employee);
        governmentId.setSss(employeeDTO.getSss());
        governmentId.setPhilhealth(employeeDTO.getPhilhealth());
        governmentId.setPagibig(employeeDTO.getPagibig());
        governmentId.setTin(employeeDTO.getTin());

        List<Allowance> allowances = new ArrayList<>();

        for (AllowanceDTO allowanceDTO: employeeDTO.getAllowances()) {
            Allowance allowance = new Allowance();
            System.out.println("Type = " +allowanceDTO.getTypeName());
            allowance.setAllowanceType(allowanceTypeRepository.findByName(allowanceDTO.getTypeName()).orElseThrow(()-> new IllegalArgumentException("Allowance type not found")));
            allowance.setAmount(allowanceDTO.getAmount());
            allowance.setEmployee(employee);
            allowances.add(allowance);
        }


        employee.setAllowances(allowances);
        employee.setSalary(salary);
        employee.setGovernmentId(governmentId);

        return employeeRepository.save(employee);
    }

    public boolean deleteEmployeeById(int employeeId) throws SQLException {
        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
            return true;
        }
        return false;
    }

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }


}
