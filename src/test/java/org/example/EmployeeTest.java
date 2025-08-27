package org.example;

import org.example.Main;
import org.example.model.*;
import org.example.repository.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class EmployeeTest {

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public PositionRepository positionRepository;

    @Autowired
    public AllowanceTypeRepository allowanceTypeRepository;

    @Autowired
    public AttendanceRepository attendanceRepository;

    @Autowired
    public SssRepository sssRepository;

    @Test
    public void add() {
        int i = 1;
        assertEquals(1,i);
    }


    @Test
    public void insertPosition() {
        Position position = new Position();
        position.setName("Driver");

        Position createdPosition = positionRepository.save(position);
        assertNotNull(createdPosition);
    }

    @Test
    public void insertEmployee() {
        Optional<Position> position = positionRepository.findById(1);
        assertTrue(position.isPresent(), "Position with ID 1 should exist");

        Salary salary = new Salary();
        salary.setBasicSalary(30000);
        salary.setGrossSemiMonthly(15000);
        salary.setHourlyRate(200);

        GovernmentId governmentId = new GovernmentId();
        governmentId.setPagibig("1234567");
        governmentId.setPhilhealth("55555");
        governmentId.setSss("444444");
        governmentId.setTin("44-44");

        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setPosition(position.get());
        employee.setSalary(salary);
        employee.setGovernmentId(governmentId);

        AllowanceType riceSubsidy = allowanceTypeRepository.findByName("Rice Subsidy").orElseThrow();
        Allowance rice = new Allowance();
        rice.setEmployee(employee);
        rice.setAllowanceType(riceSubsidy);
        rice.setAmount(500.0);

        AllowanceType phoneAllowance = allowanceTypeRepository.findByName("Phone").orElseThrow();
        Allowance phone = new Allowance();
        phone.setEmployee(employee);
        phone.setAllowanceType(phoneAllowance);
        phone.setAmount(600.0);

        AllowanceType clothingAllowance = allowanceTypeRepository.findByName("Clothing").orElseThrow();
        Allowance clothing = new Allowance();
        clothing.setEmployee(employee);
        clothing.setAllowanceType(clothingAllowance);
        clothing.setAmount(700.0);



        salary.setEmployee(employee); // bi-directional set
        governmentId.setEmployee(employee);
        employee.setAllowances(List.of(rice, phone, clothing));



        Employee saved = employeeRepository.save(employee);
        assertNotNull(saved);
    }

    @Test
    public void testGetAttendanceByEmpId() {
        int id = 1;
        List<Attendance> attendances = attendanceRepository.findByEmployeeId(id);
        System.out.println("List size: " + attendances.size());
        assertFalse(attendances.isEmpty());

    }

    @Test
    public void testSssRepo() {
        List<SssMatrix> matrix =  sssRepository.findAll();
        assertNotNull(matrix);
        assertFalse(matrix.isEmpty());
        matrix.forEach(System.out::println);
    }
}
