package org.example.dto;

import org.example.model.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PayslipDTO {
    private CreateEmployeeDTO employee;
    private double hoursWorked;
    private double grossPay;
    private double riceSubsidy;
    private double phoneAllowance;
    private double clothingAllowance;
    private double sss;
    private double philhealth;
    private double pagibig;
    private double tax;
    private double netPay;
}
