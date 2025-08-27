package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Payslip {
    private Employee employee;
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

    public double getTotalAllowances() {
        return riceSubsidy + phoneAllowance + clothingAllowance;
    }

    public double getTotalDeductions() {
        return sss + philhealth + pagibig + tax;
    }
}
