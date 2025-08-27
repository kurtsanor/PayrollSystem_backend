package org.example.service;

import org.example.model.Allowance;
import org.example.model.Attendance;
import org.example.model.Employee;
import org.example.model.Payslip;
import org.example.repository.AttendanceRepository;
import org.example.util.PayrollCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PayrollService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private DeductionService deductionService;

    @Autowired
    private EmployeeService employeeService;

    public Payslip generatePayslip(int id, int month, int year) throws SQLException {
        Employee employee = employeeService.getEmployeeById(id);
        List<Attendance> attendances = attendanceRepository.findByEmployeeIdAndMonthAndYear(employee.getId(), month, year);
        double workHours = attendances.stream().mapToDouble(Attendance::getDuration).sum();
        double grossPay = workHours * employee.getSalary().getHourlyRate();
        double rice = 0;
        double clothing = 0;
        double phone = 0;
        for (Allowance allowance : employee.getAllowances()) {
            switch (allowance.getAllowanceType().getName()) {
                case "Rice Subsidy" -> rice = allowance.getAmount();
                case "Clothing" -> clothing = allowance.getAmount();
                case "Phone" -> phone = allowance.getAmount();
            }
        }
        double sss = deductionService.getSssContribution(grossPay);
        double philhealth = deductionService.getPhilhealthContribution(grossPay);
        double pagibig = deductionService.getPagibigContribution(grossPay);
        double tax = deductionService.getTaxContribution(PayrollCalculator.getTaxableIncome(grossPay, sss, philhealth, pagibig));
        double netPay = grossPay - (sss + philhealth + pagibig + tax) + (rice + clothing + phone);

        return Payslip.builder().employee(employee)
                .hoursWorked(workHours)
                .grossPay(grossPay)
                .riceSubsidy(rice)
                .phoneAllowance(phone)
                .clothingAllowance(clothing)
                .sss(sss)
                .philhealth(philhealth)
                .pagibig(pagibig)
                .tax(tax)
                .netPay(netPay).build();
    }
}