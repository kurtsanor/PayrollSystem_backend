package org.example.util;

public class PayrollCalculator {
    public static double getTaxableIncome(double grossPay, double sss, double philhealth, double pagibig) {
        return grossPay - (sss + philhealth + pagibig);
    }

}
