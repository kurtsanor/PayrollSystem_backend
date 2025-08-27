package org.example.dto.mapper;

import org.example.dto.PayslipDTO;
import org.example.model.Payslip;

public class PayslipDTOMapper {
    public static PayslipDTO toDto(Payslip payslip) {
        return PayslipDTO.builder()
            .employee(EmployeeDTOMapper.toDTO(payslip.getEmployee()))
            .hoursWorked(payslip.getHoursWorked())
            .grossPay(payslip.getGrossPay())
            .riceSubsidy(payslip.getRiceSubsidy())
            .phoneAllowance(payslip.getPhoneAllowance())
            .clothingAllowance(payslip.getClothingAllowance())
            .sss(payslip.getSss())
            .philhealth(payslip.getPhilhealth())
            .pagibig(payslip.getPagibig())
            .tax(payslip.getTax())
            .netPay(payslip.getNetPay()).build();
    }
}
